/**
 * Created by tomasz_taw
 * Date: 15.01.2024
 * Time: 21:08
 * Project Name: chuck-norris-jokes
 * Description:
 */
package pl.taw.configuration;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "myapp")
public class AppConfig {


    @Bean
    public Dotenv dotenv() {
        return Dotenv.load();
    }

}
