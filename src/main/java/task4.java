import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class task4 {
    public static void main(String[] args) {
        String url = "https://httpbin.org/user-agent";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();

            int keyIndex = json.indexOf("\"user-agent\"");
            int colonIndex = json.indexOf(":", keyIndex);
            int startIndex = json.indexOf("\"", colonIndex) + 1;
            int endIndex = json.indexOf("\"", startIndex);

            String userAgent = json.substring(startIndex, endIndex);
            System.out.println(userAgent);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
