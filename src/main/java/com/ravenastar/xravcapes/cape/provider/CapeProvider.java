package com.ravenastar.xravcapes.cape.provider;

import java.util.UUID;

public interface CapeProvider {

    String buildUrl(String playerName, UUID playerId);
}
