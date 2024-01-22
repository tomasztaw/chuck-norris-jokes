/**
 * Created by tomasz_taw
 * Date: 22.01.2024
 * Time: 21:26
 * Project Name: chuck-norris-jokes
 * Description:
 */
package pl.taw.service;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class TextToSpeechService {

    Dotenv dotenv = Dotenv.load();
    private final String apiKey = dotenv.get("VoiceRssApiKey");

    public void playText(String text) {
        try {
            // Tworzenie polecenia
            ProcessBuilder processBuilder = new ProcessBuilder("curl", "-s",
                    "http://api.voicerss.org/?key=" + apiKey + "&hl=en-us&src=" + text, "|", "aplay");

            // Uruchamianie polecenia
            Process process = processBuilder.start();

            // Oczekiwanie na zakończenie procesu
            int exitCode = process.waitFor();

            // Sprawdzenie, czy proces zakończył się poprawnie
            if (exitCode == 0) {
                System.out.println("Polecenie wykonane poprawnie.");
            } else {
                System.err.println("Błąd wykonania polecenia. Kod błędu: " + exitCode);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
