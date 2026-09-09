package com.ravenastar.xravcapes;

import com.ravenastar.xravcapes.command.RavCapesCommand;
import com.ravenastar.xravcapes.util.XRavLog;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;

public class XRavCapesClient implements ClientModInitializer {

    public static final String MOD_ID = "xravcapes";
    @Override
    public void onInitializeClient() {
        try {
            ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) ->
                    RavCapesCommand.register(dispatcher));
        } catch (Exception e) {
            XRavLog.error("Falha ao registrar o comando /xravcapes", e);
        }

        XRavLog.info("XRAV CAPES carregado - by ravenastar.com");
    }
}
