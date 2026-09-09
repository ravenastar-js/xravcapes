package com.ravenastar.xravcapes.cape;

public enum CapeSourceType {

    OPTIFINE(1.0f),
    VANILLA(0.9f),
    OTHER(0.8f);

    private final float score;

    CapeSourceType(float score) {
        this.score = score;
    }

    public float score() {
        return score;
    }
}
