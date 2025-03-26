import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class APIFetch {
    public static void main(String[] args) {
        String apiUrl = "https://dog.ceo/api/breeds/image/random"; // Public API URL

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .build();

        try {
            // Step 1: Fetch API response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Step 2: Parse JSON dynamically
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response.body()); // Instead of hardcoding JSON, we parse the fetched response

            // Step 3: Extract the "message" field (which contains the image URL)
            String dogImageUrl = jsonNode.get("message").asText();
            System.out.println("Dog Image URL: " + dogImageUrl);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

