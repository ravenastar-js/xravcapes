package com.ravenastar.xravcapes.cape.provider;

import com.ravenastar.xravcapes.config.XRavCapesConfig;

import java.util.UUID;

public class CustomCapeProvider implements CapeProvider {

    @Override
    public String buildUrl(String playerName, UUID playerId) {
        if (!XRavCapesConfig.INSTANCE.customCapeEnabled) return null;

        String template = XRavCapesConfig.INSTANCE.customCapeUrl;
        if (template == null || template.isBlank()) return null;

        return String.format(template, playerName);
    }
}
