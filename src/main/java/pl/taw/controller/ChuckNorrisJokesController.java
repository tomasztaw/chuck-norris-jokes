/**
 * Created by tomasz_taw
 * Date: 10.01.2024
 * Time: 09:59
 * Project Name: chuck-norris-jokes
 * Description:
 */
package pl.taw.controller;

import pl.taw.api.ChuckNorrisJokesApiResponse;
import pl.taw.service.ChuckNorrisJokesService;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChuckNorrisJokesController {

    private static final Logger LOGGER = Logger.getLogger(ChuckNorrisJokesController.class.getName());

    private final ChuckNorrisJokesService chuckNorrisJokesService;

    public ChuckNorrisJokesController(ChuckNorrisJokesService chuckNorrisJokesService) {
        this.chuckNorrisJokesService = chuckNorrisJokesService;
    }

    public ChuckNorrisJokesApiResponse randomJoke() {
        LOGGER.info("randomJoke()");
        ChuckNorrisJokesApiResponse chuckNorrisJokesApiResponse = chuckNorrisJokesService.randomJoke();
        LOGGER.info("randomJoke(...) = " + chuckNorrisJokesApiResponse);

        return chuckNorrisJokesApiResponse;
    }

}
