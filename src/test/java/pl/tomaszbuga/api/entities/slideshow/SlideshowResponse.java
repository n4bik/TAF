package pl.tomaszbuga.api.entities.slideshow;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SlideshowResponse {
    @JsonProperty("slideshow")
    private Slideshow slideshow;
}