/**
 * Created by tomasz_taw
 * Date: 10.01.2024
 * Time: 09:59
 * Project Name: chuck-norris-jokes
 * Description:
 */
package pl.taw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.taw.api.ChuckNorrisJokesApiResponse;
import pl.taw.service.ChuckNorrisJokesService;

import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/random")
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

    @GetMapping("/show")
    public ModelAndView showRandomJoke() {
        ModelAndView model = new ModelAndView("show");
        model.addObject("joke", randomJoke().getValue());

        return model;
    }

    @GetMapping("/show/full")
    public ModelAndView showFullRandomJoke() {
        ModelAndView model = new ModelAndView("show-full");
        model.addObject("jokeObj", randomJoke());

        return model;
    }

    @GetMapping("/categories")
    public String showCategories(Model model) {
        List<String> categories = chuckNorrisJokesService.getCategories();
        model.addAttribute("categories", categories);

        return "categories";
    }

}
