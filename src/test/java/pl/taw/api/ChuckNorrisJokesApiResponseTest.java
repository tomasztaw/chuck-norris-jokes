package pl.taw.api;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ChuckNorrisJokesApiResponseTest {

    @Test
    void testDeserializeFromJson() throws IOException {
        // given
        String json = """
                {
                  "categories": ["dev", "nerdy"],
                  "created_at": "2024-01-25 14:30:00",
                  "icon_url": "https://example.com/icon.png",
                  "id": "123",
                  "updated_at": "2024-01-25 14:35:00",
                  "url": "https://example.com/joke/123",
                  "value": "Chuck Norris can divide by zero."
                }
                """;

        // when
        ChuckNorrisJokesApiResponse response = new ObjectMapper().readValue(json, ChuckNorrisJokesApiResponse.class);

        // then
        assertNotNull(response);
        assertEquals(2, response.getCategories().size());
        assertEquals("2024-01-25 14:30:00", response.getCreatedAt());
        assertEquals("https://example.com/icon.png", response.getIconUrl());
        assertEquals("123", response.getId());
        assertEquals("2024-01-25 14:35:00", response.getUpdatedAt());
        assertEquals("https://example.com/joke/123", response.getUrl());
        assertEquals("Chuck Norris can divide by zero.", response.getValue());
    }

    @Test
    void testSerializeToJson() throws IOException {
        // given
        ChuckNorrisJokesApiResponse response = new ChuckNorrisJokesApiResponse();
        response.setCategories(List.of("dev", "nerdy"));
        response.setCreatedAt("2024-01-25 14:30:00");
        response.setIconUrl("https://example.com/icon.png");
        response.setId("123");
        response.setUpdatedAt("2024-01-25 14:35:00");
        response.setUrl("https://example.com/joke/123");
        response.setValue("Chuck Norris can divide by zero.");

        // when
        String serializedJson = new ObjectMapper().writeValueAsString(response);

        // then
        assertNotNull(serializedJson);
    }

}