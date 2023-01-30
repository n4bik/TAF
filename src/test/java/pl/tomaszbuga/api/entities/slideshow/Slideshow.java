package pl.tomaszbuga.api.entities.slideshow;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import java.util.List;

@Data
public class Slideshow {
    @JsonProperty("author")
    private String author;
    @JsonProperty("date")
    private String date;
    @JsonProperty("slides")
    private List<Slide> slides = null;
    @JsonProperty("title")
    private String title;
}
