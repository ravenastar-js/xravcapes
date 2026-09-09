package com.ravenastar.xravcapes.cape;

import net.minecraft.util.Identifier;

public record CapeEntry(Identifier textureId, CapeSourceType type) {

    public static CapeEntry of(Identifier textureId, CapeSourceType type) {
        return new CapeEntry(textureId, type);
    }
}
