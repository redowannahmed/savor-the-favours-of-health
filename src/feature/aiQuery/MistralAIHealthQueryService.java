package feature.aiQuery;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class MistralAIHealthQueryService implements iAIHealthQuery{
    private final String apiKey;
    private final String baseUrl;

    public MistralAIHealthQueryService (String apiKey, String baseUrl)
    {
        this.apiKey = apiKey;
        this.baseUrl = baseUrl;
    }

    @Override
    public String getAIResponse (String userQuery)
    {
        String finalQuery = userQuery + " give brief, short and concise answer";
        String requestBody = "{ \"model\": \"mistral-tiny\", "
                + "\"messages\": [{\"role\": \"user\", \"content\": \"" + finalQuery + "\"}], "
                + "\"max_tokens\": 250 }";
        
                try {
                    URI uri = new URI(baseUrl);
                    HttpURLConnection conn = (HttpURLConnection) uri.toURL().openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json");
                    conn.setRequestProperty("Authorization", "Bearer " + apiKey);
                    conn.setDoOutput(true);
        
                    try (OutputStream os = conn.getOutputStream()) {
                        os.write(requestBody.getBytes(StandardCharsets.UTF_8));
                    }
        
                    try (Scanner scanner = new Scanner(conn.getInputStream(), StandardCharsets.UTF_8)) {
                        String response = scanner.useDelimiter("\\A").next();
        
                        JsonObject jsonResponse = JsonParser.parseString(response).getAsJsonObject();
                        return jsonResponse
                                .getAsJsonArray("choices")
                                .get(0)
                                .getAsJsonObject()
                                .get("message")
                                .getAsJsonObject()
                                .get("content")
                                .getAsString();
                    }
                } catch (Exception e) {
                    System.err.println("Error fetching response: " + e.getMessage());
                    return "Sorry, I couldn't fetch a response at this time.";
                }
        }
}
