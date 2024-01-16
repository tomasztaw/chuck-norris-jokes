/**
 * Created by tomasz_taw
 * Date: 16.01.2024
 * Time: 16:22
 * Project Name: chuck-norris-jokes
 * Description:
 */
package pl.taw.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.taw.api.ChuckNorrisJokesApiResponse;

import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class SpeakJokeService {

    private static final Logger LOGGER = Logger.getLogger(SpeakJokeService.class.getName());
    private final ChuckNorrisJokesService chuckNorrisJokesService;
    private final VoiceRssService voiceRssService;

    public boolean speakJoke() {
        LOGGER.info("speakJoke()");
        ChuckNorrisJokesApiResponse chuckNorrisJokesApiResponse = chuckNorrisJokesService.randomJoke();
        String joke = chuckNorrisJokesApiResponse.getValue();

        boolean spoken = voiceRssService.speakJoke(joke);
        LOGGER.info("speakJoke(...) = " + spoken);

        return spoken;
    }

}
