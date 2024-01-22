/**
 * Created by tomasz_taw
 * Date: 22.01.2024
 * Time: 21:29
 * Project Name: chuck-norris-jokes
 * Description:
 */
package pl.taw.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.taw.service.TextToSpeechService;

@RestController
@RequiredArgsConstructor
public class TextToSpeechController {

    private final TextToSpeechService textToSpeechService;

    @PostMapping("/playText")
    public void playText(@RequestParam String text) {
        textToSpeechService.playText(text);
    }
}
