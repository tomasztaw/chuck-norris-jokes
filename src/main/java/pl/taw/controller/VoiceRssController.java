/**
 * Created by tomasz_taw
 * Date: 16.01.2024
 * Time: 15:27
 * Project Name: chuck-norris-jokes
 * Description:
 */
package pl.taw.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import pl.taw.service.VoiceRssService;

import java.util.logging.Logger;

@Controller
@RequiredArgsConstructor
public class VoiceRssController {

    private static final Logger LOGGER = Logger.getLogger(VoiceRssController.class.getName());

    private final VoiceRssService voiceRssService;

    public boolean play(String textToVoice) {
        LOGGER.info("play(" + textToVoice + ")");
        boolean spoken = voiceRssService.speakJoke(textToVoice);
        LOGGER.info("play(...)" + spoken);

        return spoken;
    }
}
