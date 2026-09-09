package com.ravenastar.xravcapes.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.ravenastar.xravcapes.cape.CapeManager;
import com.ravenastar.xravcapes.gui.CapesScreen;
import com.ravenastar.xravcapes.util.XRavLog;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public final class RavCapesCommand {

    private RavCapesCommand() {}

    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(ClientCommandManager.literal("xravcapes")
                .executes(RavCapesCommand::openMenu)
                .then(ClientCommandManager.literal("reload")
                        .executes(RavCapesCommand::reloadCapes)));
    }

    private static int openMenu(CommandContext<FabricClientCommandSource> ctx) {
        try {
            MinecraftClient.getInstance().execute(() ->
                    MinecraftClient.getInstance().setScreen(new CapesScreen()));
            ctx.getSource().sendFeedback(Text.translatable("xravcapes.command.opened"));
        } catch (Exception e) {
            XRavLog.error("Erro ao abrir o menu do XRAV CAPES pelo comando", e);
            ctx.getSource().sendError(Text.literal(
                    "XRAV CAPES: erro ao abrir o menu, veja config/xravcapes/xravcapes.log"));
        }
        return 1;
    }

    private static int reloadCapes(CommandContext<FabricClientCommandSource> ctx) {
        try {
            CapeManager.get().invalidateAll();
            ctx.getSource().sendFeedback(Text.translatable("xravcapes.command.reloaded"));
        } catch (Exception e) {
            XRavLog.error("Erro ao recarregar as capas pelo comando", e);
            ctx.getSource().sendError(Text.literal(
                    "XRAV CAPES: erro ao recarregar, veja config/xravcapes/xravcapes.log"));
        }
        return 1;
    }
}
