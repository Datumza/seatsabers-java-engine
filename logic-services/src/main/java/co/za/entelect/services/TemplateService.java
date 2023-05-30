package co.za.entelect.services;

import co.za.entelect.utils.GraphUtil;
import com.microsoft.graph.models.extensions.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static co.za.entelect.constants.GraphApiUrl.V1_API_BASE_URL;

@Service
public class TemplateService {
    private final GraphUtil graphUtil;
    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    public TemplateService(GraphUtil graphUtil){
        this.graphUtil = graphUtil;
    }

    public String test() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://graph.facebook.com/v16.0/112920388470077/messages"))
                    .header("Authorization", "Bearer EAAxKMqr37I4BALApj6ZCgfOFSSA9nhjHoa0OXcZCSCMnTkcaVTx7uddTzw2m14c8RhZCm7FI5WmUWYU3ksx9YEVZAiebgSC8MybG2gAo4k3icDyu1D769OULoKVZBOixErtTZBImfMZAF05NiPsKaBK0yvMoSOhllQHdWi820ZCmdaCsWcFIyZCs4QmNlytWgeZBXxmOIZAsTZA5ZCUh8CXVieZBAl")
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

    public User testing(String token){
        HttpEntity<String> entity = graphUtil.generateEntity(token);
        return restTemplate.exchange(V1_API_BASE_URL + "me",
                HttpMethod.GET, entity, User.class).getBody();
    }

}
