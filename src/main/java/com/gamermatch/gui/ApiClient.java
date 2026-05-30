package com.gamermatch.gui;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiClient {

    private final String baseUrl;
    private final HttpClient http = HttpClient.newHttpClient();

    public ApiClient() {
        this("http://localhost:8080");
    }

    public ApiClient(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String get(String path) throws Exception {
        HttpRequest req = HttpRequest.newBuilder().uri(URI.create(baseUrl + path)).GET().build();
        HttpResponse<String> res = http.send(req, HttpResponse.BodyHandlers.ofString());
        return res.statusCode() + " | " + res.body();
    }

    public String postJson(String path, String json) throws Exception {
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + path))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
        HttpResponse<String> res = http.send(req, HttpResponse.BodyHandlers.ofString());
        return res.statusCode() + " | " + res.body();
    }

    public String post(String path) throws Exception {
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + path))
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> res = http.send(req, HttpResponse.BodyHandlers.ofString());
        return res.statusCode() + " | " + res.body();
    }
}
