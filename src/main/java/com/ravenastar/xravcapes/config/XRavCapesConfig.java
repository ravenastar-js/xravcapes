package com.ravenastar.xravcapes.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ravenastar.xravcapes.util.XRavLog;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class XRavCapesConfig {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path PATH = FabricLoader.getInstance()
            .getConfigDir().resolve("xravcapes.json");

    public static XRavCapesConfig INSTANCE = load();

    public CapeDisplayMode displayMode = CapeDisplayMode.AUTO;
    public boolean fetchOptifineCapes = true;
    public String optifineCapeUrl = "http://s.optifine.net/capes/%s.png";
    public String customCapeUrl = "";
    public boolean customCapeEnabled = false;

    private static XRavCapesConfig load() {
        if (Files.exists(PATH)) {
            try (Reader reader = Files.newBufferedReader(PATH, StandardCharsets.UTF_8)) {
                XRavCapesConfig cfg = GSON.fromJson(reader, XRavCapesConfig.class);
                if (cfg != null) return cfg;
            } catch (IOException | RuntimeException e) {
                XRavLog.warn("Não foi possível ler xravcapes.json, usando configuração padrão", e);
            }
        }
        XRavCapesConfig fresh = new XRavCapesConfig();
        fresh.save();
        return fresh;
    }

    public void save() {
        try {
            Files.createDirectories(PATH.getParent());
            try (Writer writer = Files.newBufferedWriter(PATH, StandardCharsets.UTF_8)) {
                GSON.toJson(this, writer);
            }
        } catch (IOException e) {
            XRavLog.warn("Não foi possível salvar xravcapes.json", e);
        }
    }
}
