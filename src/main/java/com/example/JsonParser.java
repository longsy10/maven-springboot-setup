import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParser {
    public static void main(String[] args) throws Exception {
        String jsonResponse = "{\"message\": \"https://images.dog.ceo/breeds/retriever-golden/n02101006_3636.jpg\", \"status\": \"success\"}"; 
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonResponse);
        System.out.println("Dog Image URL: " + jsonNode.get("message").asText());
    }
}
