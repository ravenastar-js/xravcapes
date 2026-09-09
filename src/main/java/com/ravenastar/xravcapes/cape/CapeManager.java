package com.ravenastar.xravcapes.cape;

import com.ravenastar.xravcapes.cape.net.CapeTextureDownloader;
import com.ravenastar.xravcapes.cape.provider.CustomCapeProvider;
import com.ravenastar.xravcapes.cape.provider.OptifineCapeProvider;
import com.ravenastar.xravcapes.config.CapeDisplayMode;
import com.ravenastar.xravcapes.config.XRavCapesConfig;
import com.ravenastar.xravcapes.util.XRavLog;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.util.Identifier;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

public final class CapeManager {

    private static final CapeManager INSTANCE = new CapeManager();

    private final OptifineCapeProvider optifineProvider = new OptifineCapeProvider();
    private final CustomCapeProvider customProvider = new CustomCapeProvider();

    private final Map<UUID, Optional<CapeEntry>> cache = new ConcurrentHashMap<>();
    private final Map<UUID, Boolean> loading = new ConcurrentHashMap<>();

    private CapeManager() {}

    public static CapeManager get() {
        return INSTANCE;
    }

    public Optional<CapeEntry> getCape(UUID playerId, String playerName, boolean hasVanillaCape) {
        CapeDisplayMode mode = XRavCapesConfig.INSTANCE.displayMode;
        if (mode == CapeDisplayMode.DISABLED) return Optional.empty();

        Optional<CapeEntry> cached = cache.get(playerId);
        if (cached != null) {
            return filterByMode(cached, mode);
        }

        requestResolve(playerId, playerName);
        return Optional.empty();
    }

    public void invalidate(UUID playerId) {
        cache.remove(playerId);
    }

    public void invalidateAll() {
        cache.clear();
    }

    private Optional<CapeEntry> filterByMode(Optional<CapeEntry> resolved, CapeDisplayMode mode) {
        return switch (mode) {
            case AUTO -> resolved;
            case OPTIFINE -> resolved.filter(entry -> entry.type() == CapeSourceType.OPTIFINE);
            case VANILLA -> Optional.empty();
            case DISABLED -> Optional.empty();
        };
    }

    private void requestResolve(UUID playerId, String playerName) {
        if (loading.putIfAbsent(playerId, Boolean.TRUE) != null) return;

        CompletableFuture.supplyAsync(() -> downloadBestImage(playerId, playerName))
                .thenCompose(this::registerIfPresent)
                .whenComplete((entry, throwable) -> {
                    if (throwable != null) {
                        XRavLog.warn("Erro ao resolver a capa de " + playerName, throwable);
                    }
                    cache.put(playerId, Optional.ofNullable(entry));
                    loading.remove(playerId);
                });
    }

    private PendingCape downloadBestImage(UUID playerId, String playerName) {
        try {
            if (XRavCapesConfig.INSTANCE.fetchOptifineCapes) {
                NativeImage optifineImage = CapeTextureDownloader.download(optifineProvider, playerName, playerId);
                if (optifineImage != null) {
                    return new PendingCape(optifineImage, CapeSourceType.OPTIFINE, playerId);
                }
            }

            NativeImage customImage = CapeTextureDownloader.download(customProvider, playerName, playerId);
            if (customImage != null) {
                return new PendingCape(customImage, CapeSourceType.OTHER, playerId);
            }
            return null;
        } catch (Exception e) {
            XRavLog.warn("Erro inesperado ao resolver a capa de " + playerName, e);
            return null;
        }
    }

    private CompletableFuture<CapeEntry> registerIfPresent(PendingCape pending) {
        if (pending == null) return CompletableFuture.completedFuture(null);

        CompletableFuture<CapeEntry> future = new CompletableFuture<>();
        Identifier textureId = Identifier.of("xravcapes",
                "cape/" + pending.playerId() + "_" + pending.type().name().toLowerCase());

        MinecraftClient.getInstance().execute(() -> {
            try {
                MinecraftClient.getInstance().getTextureManager()
                        .registerTexture(textureId, new NativeImageBackedTexture(textureId::toString, pending.image()));
                future.complete(CapeEntry.of(textureId, pending.type()));
            } catch (Exception e) {
                XRavLog.error("Erro ao registrar a textura da capa " + textureId, e);
                future.complete(null);
            }
        });
        return future;
    }

    private record PendingCape(NativeImage image, CapeSourceType type, UUID playerId) {}
}
