package pl.tomaszbuga.api.entities.apiServer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Comment {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("body")
    private Body body;
    @JsonProperty("postId")
    private Integer postId;
}