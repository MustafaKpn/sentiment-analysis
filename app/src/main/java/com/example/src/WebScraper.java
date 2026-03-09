import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;



public class WebScraper {

    // create request
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("https://www.reuters.com/site-search/?query=apple&offset=0"))
        .GET()
        .build();

    // send request
    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    String jsonResponse = response.body();

    
}