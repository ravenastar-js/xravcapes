package com.ravenastar.xravcapes.util;

import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class XRavLog {

    private static final Logger LOGGER = LoggerFactory.getLogger("XRAV CAPES");
    private static final DateTimeFormatter TIMESTAMP = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final Path LOG_FILE = FabricLoader.getInstance()
            .getConfigDir().resolve("xravcapes").resolve("xravcapes.log");

    private XRavLog() {}

    public static void info(String message) {
        LOGGER.info(message);
        write("INFO", message, null);
    }

    public static void warn(String message) {
        LOGGER.warn(message);
        write("WARN", message, null);
    }

    public static void warn(String message, Throwable t) {
        LOGGER.warn(message, t);
        write("WARN", message, t);
    }

    public static void error(String message, Throwable t) {
        LOGGER.error(message, t);
        write("ERROR", message, t);
    }

    private static synchronized void write(String level, String message, Throwable t) {
        try {
            Files.createDirectories(LOG_FILE.getParent());

            StringBuilder sb = new StringBuilder();
            sb.append('[').append(LocalDateTime.now().format(TIMESTAMP)).append("] [")
                    .append(level).append("] ").append(message).append(System.lineSeparator());

            if (t != null) {
                StringWriter sw = new StringWriter();
                t.printStackTrace(new PrintWriter(sw));
                sb.append(sw);
            }

            Files.writeString(LOG_FILE, sb.toString(), StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            LOGGER.warn("Não foi possível gravar em xravcapes.log", e);
        }
    }
}
