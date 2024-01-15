/**
 * Created by tomasz_taw
 * Date: 12.01.2024
 * Time: 18:14
 * Project Name: chuck-norris-jokes
 * Description:
 */
package pl.taw.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChuckNorrisCategoriesResponse {

    private String[] categories;


}
