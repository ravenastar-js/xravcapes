package com.ravenastar.xravcapes.cape.net;

import com.ravenastar.xravcapes.cape.provider.CapeProvider;
import com.ravenastar.xravcapes.util.XRavLog;
import net.minecraft.client.texture.NativeImage;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.UUID;

public final class CapeTextureDownloader {

    private static final HttpClient HTTP_CLIENT = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(5))
            .build();

    private static final int CAPE_BASE_WIDTH = 64;
    private static final int CAPE_BASE_HEIGHT = 32;

    private CapeTextureDownloader() {}

    public static NativeImage download(CapeProvider provider, String playerName, UUID playerId) {
        String url = provider.buildUrl(playerName, playerId);
        if (url == null || url.isBlank()) return null;

        try {
            NativeImage raw = fetch(url);
            if (raw == null) return null;

            return normalizeToCapeCanvas(raw);
        } catch (IOException | InterruptedException e) {
            if (Thread.currentThread().isInterrupted()) {
                Thread.currentThread().interrupt();
            }
            XRavLog.warn("Falha ao baixar capa de " + url, e);
            return null;
        } catch (Exception e) {
            XRavLog.warn("Erro inesperado ao processar a capa de " + url, e);
            return null;
        }
    }

    private static NativeImage fetch(String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(6))
                .GET()
                .build();

        HttpResponse<byte[]> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofByteArray());
        if (response.statusCode() != 200) return null;

        return NativeImage.read(response.body());
    }

    private static NativeImage normalizeToCapeCanvas(NativeImage source) {
        int width = source.getWidth();
        int height = source.getHeight();

        int canvasWidth = CAPE_BASE_WIDTH;
        int canvasHeight = CAPE_BASE_HEIGHT;
        while (width > canvasWidth || height > canvasHeight) {
            canvasWidth *= 2;
            canvasHeight *= 2;
        }

        if (width == canvasWidth && height == canvasHeight) {
            return source;
        }

        NativeImage canvas = new NativeImage(canvasWidth, canvasHeight, true);
        canvas.fillRect(0, 0, canvasWidth, canvasHeight, 0);
        source.copyRect(canvas, 0, 0, 0, 0, width, height, false, false);
        source.close();
        return canvas;
    }
}
