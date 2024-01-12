/**
 * Created by tomasz_taw
 * Date: 09.01.2024
 * Time: 17:02
 * Project Name: chuck-norris-jokes
 * Description:
 */
package pl.taw.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;
import pl.taw.api.ChuckNorrisJokesApiResponse;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ChuckNorrisJokesService {

    private static final Logger LOGGER = Logger.getLogger(ChuckNorrisJokesService.class.getName());
    private static final String API_URL_RANDOM_JOKE = "https://api.chucknorris.io/jokes/random";
    private static final String API_CATEGORIES = "https://api.chucknorris.io/jokes/categories";
    private final OkHttpClient client = new OkHttpClient();


    public List<String> getCategories() {
        try {
            Request request = new Request.Builder()
                    .url(API_CATEGORIES)
                    .build();

            Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                LOGGER.info("getCategories() response successful!");
                assert response.body() != null;
                String responseBody = response.body().string();
                ObjectMapper objectMapper = new ObjectMapper();

                return objectMapper.readValue(responseBody, new TypeReference<>() {});

            } else {
                throw new RuntimeException("There is a problem with response. Response code: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    public ChuckNorrisJokesApiResponse randomJoke() {
        LOGGER.info("randomJoke()");
        try {
            String responseBody = getResponse(API_URL_RANDOM_JOKE);
            ChuckNorrisJokesApiResponse chuckNorrisJokesApiResponse = convert(responseBody);
            LOGGER.info("randomJoke(...) = " + chuckNorrisJokesApiResponse);

            return chuckNorrisJokesApiResponse;

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Unable to connect with external API.", e);
        }

        LOGGER.info("randomJoke(...) = " + null);

        return null;
    }

    public String getResponse(String url) throws IOException {
        LOGGER.info("run(" + url + ")");
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            assert response.body() != null;
            String body = response.body().string();
            LOGGER.info("run(...) = " + body);
            return body;
        }
    }

    public ChuckNorrisJokesApiResponse convert(String body) {
        LOGGER.info("convert(" + body + ")");
        Gson gson = new Gson();
        ChuckNorrisJokesApiResponse chuckNorrisJokesApiResponse = gson.fromJson(
                body, ChuckNorrisJokesApiResponse.class);
        LOGGER.info("convert(...) = " + chuckNorrisJokesApiResponse);

        return chuckNorrisJokesApiResponse;
    }
}
