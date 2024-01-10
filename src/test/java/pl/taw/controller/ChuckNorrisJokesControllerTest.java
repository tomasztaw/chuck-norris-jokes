package pl.taw.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.taw.api.ChuckNorrisJokesApiResponse;
import pl.taw.service.ChuckNorrisJokesService;

import static org.junit.jupiter.api.Assertions.*;

class ChuckNorrisJokesControllerTest {

    @Test
    void randomJoke() {
        // given
        ChuckNorrisJokesService chuckNorrisJokesService = new ChuckNorrisJokesService();
        ChuckNorrisJokesController chuckNorrisJokesController = new ChuckNorrisJokesController(chuckNorrisJokesService);

        // when
        ChuckNorrisJokesApiResponse chuckNorrisJokesApiResponse = chuckNorrisJokesController.randomJoke();

        // then
        Assertions.assertNotNull(chuckNorrisJokesApiResponse, "response is NULL!");
    }
}