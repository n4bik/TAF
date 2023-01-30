package pl.tomaszbuga.api.entities.slideshow;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import java.util.List;

@Data
public class Slide {
    @JsonProperty("title")
    private String title;
    @JsonProperty("type")
    private String type;
    @JsonProperty("items")
    private List<String> items = null;
}
