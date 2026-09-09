package com.ravenastar.xravcapes.gui;

import com.ravenastar.xravcapes.cape.CapeEntry;
import com.ravenastar.xravcapes.cape.CapeManager;
import com.ravenastar.xravcapes.config.CapeDisplayMode;
import com.ravenastar.xravcapes.config.XRavCapesConfig;
import com.ravenastar.xravcapes.util.XRavLog;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.CyclingButtonWidget;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.text.Text;

import java.util.Optional;

public class CapesScreen extends Screen {

    private static final int PANEL_WIDTH = 220;
    private static final int PANEL_HEIGHT = 150;

    public CapesScreen() {
        super(Text.translatable("xravcapes.screen.title"));
    }

    @Override
    protected void init() {
        int centerX = width / 2;
        int top = height / 2 - PANEL_HEIGHT / 2;

        addDrawableChild(CyclingButtonWidget.<CapeDisplayMode>builder(this::modeLabel, XRavCapesConfig.INSTANCE.displayMode)
                .values(CapeDisplayMode.values())
                .build(centerX - 100, top + 70, 200, 20,
                        Text.translatable("xravcapes.screen.source"),
                        (button, value) -> {
                            XRavCapesConfig.INSTANCE.displayMode = value;
                            XRavCapesConfig.INSTANCE.save();
                        }));

        addDrawableChild(ButtonWidget.builder(Text.translatable("xravcapes.screen.reload"),
                        button -> CapeManager.get().invalidateAll())
                .dimensions(centerX - 100, top + 96, 200, 20)
                .build());

        addDrawableChild(ButtonWidget.builder(Text.translatable("xravcapes.screen.close"), button -> close())
                .dimensions(centerX - 100, top + 122, 200, 20)
                .build());
    }

    private Text modeLabel(CapeDisplayMode mode) {
        return switch (mode) {
            case AUTO -> Text.translatable("xravcapes.screen.source.auto");
            case OPTIFINE -> Text.translatable("xravcapes.screen.source.optifine");
            case VANILLA -> Text.translatable("xravcapes.screen.source.vanilla");
            case DISABLED -> Text.translatable("xravcapes.screen.source.off");
        };
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        try {
            context.fill(0, 0, width, height, 0xC0101010);

            int centerX = width / 2;
            int top = height / 2 - PANEL_HEIGHT / 2;

            context.drawCenteredTextWithShadow(textRenderer, title, centerX, top, 0xFFFFFF);
            renderPreview(context, centerX, top + 30);

            super.render(context, mouseX, mouseY, delta);

            context.drawCenteredTextWithShadow(textRenderer,
                    Text.translatable("xravcapes.screen.credits"),
                    centerX, height - 14, 0x808080);
        } catch (Exception e) {
            XRavLog.error("Erro ao renderizar o menu do XRAV CAPES, fechando a tela para evitar crash", e);
            close();
        }
    }

    private void renderPreview(DrawContext context, int centerX, int y) {
        try {
            if (client == null || client.player == null) return;

            PlayerListEntry entry = client.getNetworkHandler() != null
                    ? client.getNetworkHandler().getPlayerListEntry(client.player.getUuid())
                    : null;

            String status;
            if (entry != null && entry.getSkinTextures().cape() != null) {
                Optional<CapeEntry> resolved = CapeManager.get().getCape(
                        client.player.getUuid(), client.player.getGameProfile().name(), true);
                status = resolved.map(e -> "xravcapes.priority." + e.type().name().toLowerCase())
                        .map(key -> Text.translatable(key).getString())
                        .orElse(Text.translatable("xravcapes.priority.vanilla").getString());
            } else {
                status = Text.translatable("xravcapes.screen.source.off").getString();
            }

            context.drawCenteredTextWithShadow(textRenderer,
                    Text.translatable("xravcapes.screen.preview").getString() + ": " + status,
                    centerX, y, 0xFFFFFF);
        } catch (Exception e) {
            XRavLog.warn("Erro ao gerar a pré-visualização da capa", e);
        }
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
