package pl.taw.api;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "categories",
        "created_at",
        "icon_url",
        "id",
        "updated_at",
        "url",
        "value"
})
@Generated("jsonschema2pojo")
public class ChuckNorrisJokesApiResponse {

    @JsonProperty("categories")
    private List<Object> categories;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("icon_url")
    private String iconUrl;
    @JsonProperty("id")
    private String id;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("url")
    private String url;
    @JsonProperty("value")
    private String value;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("categories")
    public List<Object> getCategories() {
        return categories;
    }

    @JsonProperty("categories")
    public void setCategories(List<Object> categories) {
        this.categories = categories;
    }

    @JsonProperty("created_at")
    public String getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("created_at")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("icon_url")
    public String getIconUrl() {
        return iconUrl;
    }

    @JsonProperty("icon_url")
    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("updated_at")
    public String getUpdatedAt() {
        return updatedAt;
    }

    @JsonProperty("updated_at")
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("value")
    public String getValue() {
        return value;
    }

    @JsonProperty("value")
    public void setValue(String value) {
        this.value = value;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return "ChuckNorrisJokesApiResponse{" +
                "categories=" + categories +
                ", createdAt='" + createdAt + '\'' +
                ", iconUrl='" + iconUrl + '\'' +
                ", id='" + id + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", url='" + url + '\'' +
                ", value='" + value + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}