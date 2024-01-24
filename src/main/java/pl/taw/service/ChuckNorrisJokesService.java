/**
 * Created by tomasz_taw
 * Date: 09.01.2024
 * Time: 17:02
 * Project Name: chuck-norris-jokes
 * Description:
 */
package pl.taw.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;
import pl.taw.api.ChuckNorrisJokesApiResponse;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ChuckNorrisJokesService {

    private static final String API_URL_RANDOM_JOKE = "https://api.chucknorris.io/jokes/random";
    private static final String API_CATEGORIES = "https://api.chucknorris.io/jokes/categories";
    private static final String API_JOKE_BY_CATEGORY = "https://api.chucknorris.io/jokes/random?category=";
    private final OkHttpClient client = new OkHttpClient();

    private final ObjectMapper objectMapper;



    public List<String> getCategories() {
        try {
            Request request = new Request.Builder()
                    .url(API_CATEGORIES)
                    .build();

            Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                log.info("getCategories() response successful!");
                assert response.body() != null;
                String responseBody = response.body().string();
                ObjectMapper objectMapper = new ObjectMapper();

                return objectMapper.readValue(responseBody, new TypeReference<>() {});

            } else {
                throw new RuntimeException("There is a problem with response. Response code: " + response.code());
            }
        } catch (IOException e) {
            log.error("An error occurred while processing the joke request", e);
            return Collections.emptyList();
        }
    }

    public ChuckNorrisJokesApiResponse randomJoke() {
        log.info("randomJoke()");
        try {
            String responseBody = getResponse(API_URL_RANDOM_JOKE);
            ChuckNorrisJokesApiResponse chuckNorrisJokesApiResponse = convert(responseBody);
            log.info("randomJoke(...) = " + chuckNorrisJokesApiResponse);

            return chuckNorrisJokesApiResponse;

        } catch (IOException e) {
            log.error("Unable to connect with external API.", e);
        }
        log.info("randomJoke(...) = " + null);

        return null;
    }

    public String getResponse(String url) throws IOException {
        log.info("run(" + url + ")");
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            assert response.body() != null;
            String body = response.body().string();
            log.info("run(...) = " + body);

            return body;
        }  finally {
            client.dispatcher().executorService().shutdown();
            client.connectionPool().evictAll();
        }
    }

    public ChuckNorrisJokesApiResponse convert(String body) {
        log.info("convert(" + body + ")");
        Gson gson = new Gson();
        ChuckNorrisJokesApiResponse chuckNorrisJokesApiResponse = gson.fromJson(
                body, ChuckNorrisJokesApiResponse.class);
        log.info("convert(...) = " + chuckNorrisJokesApiResponse);

        return chuckNorrisJokesApiResponse;
    }

    public String jokeByCategory(String category) {
        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            URI uri = URI.create(API_JOKE_BY_CATEGORY + category);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                JsonNode jsonNode = objectMapper.readTree(response.body());
                return jsonNode.get("value").asText();
            } else {
                System.out.println("Request failed with status code: " + response.statusCode());
                return null;
            }
        } catch (Exception e) {
            log.error("An error occurred while processing the joke request", e);
            return null;
        }
    }
}
