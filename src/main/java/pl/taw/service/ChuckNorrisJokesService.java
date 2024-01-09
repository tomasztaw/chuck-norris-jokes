/**
 * Created by tomasz_taw
 * Date: 09.01.2024
 * Time: 17:02
 * Project Name: chuck-norris-jokes
 * Description:
 */
package pl.taw.service;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import pl.taw.api.ChuckNorrisJokesApiResponse;

import java.io.IOException;
import java.util.logging.Logger;

public class ChuckNorrisJokesService {

    private static final Logger LOGGER = Logger.getLogger(ChuckNorrisJokesService.class.getName());
    private OkHttpClient client = new OkHttpClient();

    public String run(String url) throws IOException {
        LOGGER.info("run(" + url + ")");
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
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
