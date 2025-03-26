package com.example;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Apiclient {
    public static void main(String[] args) {
        String apiUrl = "http://localhost:8080/greet?name=Student"; // Public API URL

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("API Response: " + response.body());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
