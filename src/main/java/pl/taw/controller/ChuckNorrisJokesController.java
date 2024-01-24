/**
 * Created by tomasz_taw
 * Date: 10.01.2024
 * Time: 09:59
 * Project Name: chuck-norris-jokes
 * Description:
 */
package pl.taw.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.taw.api.ChuckNorrisJokesApiResponse;
import pl.taw.service.ChuckNorrisJokesService;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/random")
public class ChuckNorrisJokesController {

    private final ChuckNorrisJokesService chuckNorrisJokesService;

    public ChuckNorrisJokesController(ChuckNorrisJokesService chuckNorrisJokesService) {
        this.chuckNorrisJokesService = chuckNorrisJokesService;
    }

    public ChuckNorrisJokesApiResponse randomJoke() {
        log.info("randomJoke()");
        ChuckNorrisJokesApiResponse chuckNorrisJokesApiResponse = chuckNorrisJokesService.randomJoke();
        log.info("randomJoke(...) = " + chuckNorrisJokesApiResponse);

        return chuckNorrisJokesApiResponse;
    }

    @GetMapping("/show")
    public ModelAndView showRandomJoke() {
        ModelAndView model = new ModelAndView("show");
        model.addObject("joke", randomJoke().getValue());

        return model;
    }

    @GetMapping("/categories")
    public String showCategories(Model model) {
        List<String> categories = chuckNorrisJokesService.getCategories();
        model.addAttribute("categories", categories);

        return "categories";
    }

    @GetMapping("/category")
    public String getJokeByCategory(@RequestParam String category, Model model) {
        String joke = chuckNorrisJokesService.jokeByCategory(category);
        model.addAttribute("joke", joke);
        model.addAttribute("category", category);

        return "jokeByCategory";
    }

}
