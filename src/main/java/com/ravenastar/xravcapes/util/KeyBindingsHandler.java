package com.ravenastar.xravcapes.util;

import com.ravenastar.xravcapes.gui.CapesScreen;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

public final class KeyBindingsHandler {

    private static final KeyBinding.Category CATEGORY =
            KeyBinding.Category.create(Identifier.of("xravcapes", "main"));

    public static KeyBinding openMenu;

    private KeyBindingsHandler() {}

    public static void register() {
        openMenu = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "xravcapes.key.openMenu",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_K,
                CATEGORY
        ));

        ClientTickEvents.END_CLIENT_TICK.register(KeyBindingsHandler::handleTick);
    }

    private static void handleTick(MinecraftClient client) {
        try {
            while (openMenu.wasPressed()) {
                if (client.currentScreen == null) {
                    client.setScreen(new CapesScreen());
                }
            }
        } catch (Exception e) {
            XRavLog.error("Erro ao processar o atalho de teclado do XRAV CAPES", e);
        }
    }
}
