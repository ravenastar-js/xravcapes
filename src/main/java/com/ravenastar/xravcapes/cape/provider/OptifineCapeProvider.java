package com.ravenastar.xravcapes.cape.provider;

import com.ravenastar.xravcapes.config.XRavCapesConfig;

import java.util.UUID;

public class OptifineCapeProvider implements CapeProvider {

    @Override
    public String buildUrl(String playerName, UUID playerId) {
        return String.format(XRavCapesConfig.INSTANCE.optifineCapeUrl, playerName);
    }
}
