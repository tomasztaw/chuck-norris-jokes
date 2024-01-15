/**
 * Created by tomasz_taw
 * Date: 15.01.2024
 * Time: 20:01
 * Project Name: chuck-norris-jokes
 * Description:
 */
package pl.taw.service;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.AllArgsConstructor;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

@Service
@AllArgsConstructor
public class VoiceRssService {

    private static final Logger LOGGER = Logger.getLogger(VoiceRssService.class.getName());
    private final AudioPlayService audioPlayService;
    Dotenv dotenv = Dotenv.load();
    private final String voiceRssApiKey = dotenv.get("VoiceRssApiKey");
    private final OkHttpClient client = new OkHttpClient();


    public boolean speakJoke(String textToVoice) {
        LOGGER.info("speakJoke(" + textToVoice + ")");
        boolean spoken = false;

        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("http").host("api.voicerss.org")
                .addQueryParameter("key", voiceRssApiKey)
                .addQueryParameter("src", textToVoice)
                .build();

        Request request = new Request.Builder().url(httpUrl).build();

        try (Response response = client.newCall(request).execute()) {
            ResponseBody responseBody = response.body();
            if (responseBody != null) {
                InputStream inputStream = new ByteArrayInputStream(responseBody.bytes());
                audioPlayService.play(inputStream);
                spoken = true;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return spoken;
    }
}
