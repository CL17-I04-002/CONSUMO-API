package com.consumo.api.client;

import com.consumo.api.util.Endpoints;
import com.consumo.api.util.Serializer;
import lombok.NonNull;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class RequestHandler {
    public <T>Map<String, String> sendPOSTRequest(T serializerObjet, String endpoint, String contentType, @NonNull String token){
        Map<String, String> mapDataData = new HashMap<>();
        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(25))
                .version(HttpClient.Version.HTTP_2)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();
        HttpRequest request = null;
        if(token!=null){
            request =   HttpRequest.newBuilder()
                        .uri(URI.create(endpoint))
                        .headers("Content-Type", contentType, "Authorization", "Bearer " + token)
                        .POST(HttpRequest.BodyPublishers.ofString(Serializer.serialize(serializerObjet), StandardCharsets.UTF_8))
                        .build();
        }else{
            request =   HttpRequest.newBuilder()
                        .uri(URI.create(endpoint))
                        .header("Content-Type", contentType)
                        .POST(HttpRequest.BodyPublishers.ofString(Serializer.serialize(serializerObjet), StandardCharsets.UTF_8))
                        .build();
        }
        try {
            HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = response.statusCode();
            String body = response.body().toString();
            System.out.println("Status Code: " + statusCode);
            System.out.println("Body: " + body);
            mapDataData.put("status", String.valueOf(statusCode));
            mapDataData.put("body", body);
            return mapDataData;

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public <T>Map<String, String> extractToken(T serializerObjet, String endpoint, String contentType){
        Map<String, String> mapDataData = new HashMap<>();
        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(25))
                .version(HttpClient.Version.HTTP_2)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();


            HttpRequest request =   HttpRequest.newBuilder()
                    .uri(URI.create(endpoint))
                    .header("Content-Type", contentType)
                    .POST(HttpRequest.BodyPublishers.ofString(Serializer.serialize(serializerObjet), StandardCharsets.UTF_8))
                    .build();

        try {
            HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = response.statusCode();
            String body = response.body().toString();
            String token = extractTokenFromBody(body);
            System.out.println("Status Code: " + statusCode);
            System.out.println("Body: " + body);
            mapDataData.put("status", String.valueOf(statusCode));
            mapDataData.put("body", body);
            mapDataData.put("token", token);
            return mapDataData;

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    private String extractTokenFromBody(String body) {
        String regex = "\"jwt\":\"([^\"]+)\"";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(body);

        if (matcher.find()) {
            return matcher.group(1);
        }

        return null; // El token no se encontró en el formato esperado
    }
}
