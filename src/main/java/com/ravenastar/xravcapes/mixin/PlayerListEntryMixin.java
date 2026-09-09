package com.ravenastar.xravcapes.mixin;

import com.mojang.authlib.GameProfile;
import com.ravenastar.xravcapes.cape.CapeEntry;
import com.ravenastar.xravcapes.cape.CapeManager;
import com.ravenastar.xravcapes.util.XRavLog;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.entity.player.SkinTextures;
import net.minecraft.util.AssetInfo;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Mixin(PlayerListEntry.class)
public abstract class PlayerListEntryMixin {

    private static final Set<UUID> LOGGED_PLAYERS = ConcurrentHashMap.newKeySet();

    @Inject(method = "getSkinTextures", at = @At("RETURN"), cancellable = true)
    private void xravcapes$overrideCape(CallbackInfoReturnable<SkinTextures> cir) {
        try {
            PlayerListEntry self = (PlayerListEntry) (Object) this;
            GameProfile profile = self.getProfile();
            if (profile == null) return;

            SkinTextures original = cir.getReturnValue();
            boolean hasVanillaCape = original.cape() != null;

            CapeManager.get()
                    .getCape(profile.id(), profile.name(), hasVanillaCape)
                    .ifPresent(entry -> cir.setReturnValue(applyCape(original, entry, profile)));
        } catch (Exception e) {
            XRavLog.error("Erro no mixin de capas (PlayerListEntryMixin), mantendo a capa original", e);
        }
    }

    private SkinTextures applyCape(SkinTextures original, CapeEntry entry, GameProfile profile) {
        Identifier textureId = entry.textureId();

        if (LOGGED_PLAYERS.add(profile.id())) {
            XRavLog.info("[xravcapes] player=" + profile.name()
                    + " uuid=" + profile.id()
                    + " source=" + entry.type()
                    + " textureId=" + textureId);
        }

        AssetInfo.TextureAsset capeAsset = new AssetInfo.TextureAssetInfo(textureId, textureId);

        return new SkinTextures(
                original.body(),
                capeAsset,
                original.elytra(),
                original.model(),
                original.secure()
        );
    }
}
