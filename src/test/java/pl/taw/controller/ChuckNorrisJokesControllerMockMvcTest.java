package pl.taw.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.taw.api.ChuckNorrisJokesApiResponse;
import pl.taw.service.ChuckNorrisJokesService;

import java.util.Collections;

import static org.mockito.Mockito.when;

@WebMvcTest(ChuckNorrisJokesController.class)
class ChuckNorrisJokesControllerMockMvcTest {

    @MockBean
    @SuppressWarnings("unused")
    private ChuckNorrisJokesService chuckNorrisJokesService;

    @Autowired
    @SuppressWarnings("unused")
    private MockMvc mockMvc;

    @Test
    void testShowRandomJoke() throws Exception {
        // given
        ChuckNorrisJokesApiResponse response = new ChuckNorrisJokesApiResponse();
        response.setValue("Chuck Norris can write multi-threaded applications with a single thread.");

        when(chuckNorrisJokesService.randomJoke()).thenReturn(response);

        // when, then
        mockMvc.perform(MockMvcRequestBuilders.get("/random/show"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("show"))
                .andExpect(MockMvcResultMatchers.model().attribute("joke", response.getValue()));
    }

    @Test
    void testShowCategories() throws Exception {
        // given
        when(chuckNorrisJokesService.getCategories()).thenReturn(Collections.singletonList("dev"));

        // when, then
        mockMvc.perform(MockMvcRequestBuilders.get("/random/categories"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("categories"))
                .andExpect(MockMvcResultMatchers.model().attribute("categories", Collections.singletonList("dev")));
    }

    @Test
    void testGetJokeByCategory() throws Exception {
        // given
        String category = "dev";
        String joke = "When Chuck Norris throws exceptions, it's across the room.";

        when(chuckNorrisJokesService.jokeByCategory(category)).thenReturn(joke);

        // when, then
        mockMvc.perform(MockMvcRequestBuilders.get("/random/category").param("category", category))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("jokeByCategory"))
                .andExpect(MockMvcResultMatchers.model().attribute("joke", joke))
                .andExpect(MockMvcResultMatchers.model().attribute("category", category));
    }
}