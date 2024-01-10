package pl.taw.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.taw.api.ChuckNorrisJokesApiResponse;

import java.io.IOException;

class ChuckNorrisJokesServiceTest {

    @Test
    void shouldByNotNull() throws IOException {
        // given
        ChuckNorrisJokesService chuckNorrisJokesService = new ChuckNorrisJokesService();
        String url = "https://api.chucknorris.io/jokes/random";

        // when
        String response = chuckNorrisJokesService.getResponse(url);

        // then
        Assertions.assertNotNull(response, "response is NULL!");
    }

    @Test
    void convert() {
        // given
        ChuckNorrisJokesService chuckNorrisJokesService = new ChuckNorrisJokesService();

        // when
        ChuckNorrisJokesApiResponse chuckNorrisJokesApiResponse = chuckNorrisJokesService.convert("{\n" +
                "\"icon_url\" : \"https://assets.chucknorris.host/img/avatar/chuck-norris.png\",\n" +
                "\"id\" : \"nEg3keN7TsW7qFKf5H92Xg\",\n" +
                "\"url\" : \"\",\n" +
                "\"value\" : \"Chuck Norris has never been dubbed in any film, because his fists do the talking.\"\n" +
                "}");

        // then
        Assertions.assertNotNull(chuckNorrisJokesApiResponse, "response is NULL!");
    }

    @Test
    void randomJoke() {
        // given
        ChuckNorrisJokesService chuckNorrisJokesService = new ChuckNorrisJokesService();

        // when
        ChuckNorrisJokesApiResponse chuckNorrisJokesApiResponse = chuckNorrisJokesService.randomJoke();

        // then
        Assertions.assertNotNull(chuckNorrisJokesApiResponse, "response is NULL!");

    }
}