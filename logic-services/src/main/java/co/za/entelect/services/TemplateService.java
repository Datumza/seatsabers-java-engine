package co.za.entelect.services;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class TemplateService {

    public String test() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://graph.facebook.com/v16.0/112920388470077/messages"))
                    .header("Authorization", "Bearer EAAxKMqr37I4BALjz6SckPn2zQgxZAcaZCRhfDhGG1vXe6GjcoV26evw5BVRPzaS00kGsiYjH5PmGk1fBJXXR3SlQNTHgecFEsFAE6EAHGxnZCaEVJ1VnkQiPszL4vy6GjLZBQZAmrndqAZAzexbQqsAYZCbNaPeH7KVZAqgEjKji1ZAzkHxI4awcF8dbmdrUN0oopXQZB1hgCErnDufej1OCG1")
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString("{ \"messaging_product\": \"whatsapp\", \"recipient_type\": \"individual\", \"to\": \"27838599234\", \"type\": \"template\", \"template\": { \"name\": \"hello_world\", \"language\": { \"code\": \"en_US\" } } }"))
                    //.POST(HttpRequest.BodyPublishers.ofString("{ \"messaging_product\": \"whatsapp\", \"recipient_type\": \"individual\", \"to\": \"27838599234\", \"type\": \"text\", \"text\": { \"preview_url\": false, \"body\": \"This is an example of a text message\" } }"))
                    .build();
            HttpClient http = HttpClient.newHttpClient();
            HttpResponse<String> response = http.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();

        } catch (URISyntaxException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

}