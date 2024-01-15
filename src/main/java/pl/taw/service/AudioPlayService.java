/**
 * Created by tomasz_taw
 * Date: 15.01.2024
 * Time: 20:46
 * Project Name: chuck-norris-jokes
 * Description:
 */
package pl.taw.service;

import org.springframework.stereotype.Service;

import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;

@Service
public class AudioPlayService {

    private static final int BUFFER_SIZE = 4096;

    public void play(InputStream inputStream) {
        try (AudioInputStream audioStream = AudioSystem.getAudioInputStream(inputStream)) {

            AudioFormat audioFormat = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
            SourceDataLine sourceDataLine = (SourceDataLine) AudioSystem.getLine(info);
            sourceDataLine.open(audioFormat);
            sourceDataLine.start();

            System.out.println("Playback Started.");

            byte[] bufferBytes = new byte[BUFFER_SIZE];
            int readBytes;
            while ((readBytes = audioStream.read(bufferBytes)) != -1) {
                sourceDataLine.write(bufferBytes, 0, readBytes);
            }

            sourceDataLine.drain();
            System.out.println("Playback has been finished.");

        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException ex) {
            System.err.println("Error occurred during playback process: " + ex.getMessage());
            // Obsługa błędów
        }
    }


}
