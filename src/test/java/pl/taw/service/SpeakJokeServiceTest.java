package pl.taw.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpeakJokeServiceTest {

    @Test
    void speakJoke() {
        // given
        Dotenv dotenv = Dotenv.load();
        AudioPlayService audioPlayService = new AudioPlayService();
        VoiceRssService voiceRssService = new VoiceRssService(audioPlayService, dotenv);
        ChuckNorrisJokesService chuckNorrisJokesService = new ChuckNorrisJokesService(new ObjectMapper());
        SpeakJokeService speakJokeService = new SpeakJokeService(chuckNorrisJokesService, voiceRssService);

        // when
        boolean spoken = speakJokeService.speakJoke();

        // then
        Assertions.assertTrue(spoken, "joke not spoken");
    }
}