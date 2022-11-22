package pl.tomaszbuga.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.tomaszbuga.entities.mockserver.Body;
import pl.tomaszbuga.entities.mockserver.Comment;
import pl.tomaszbuga.entities.slideshow.Slideshow;
import pl.tomaszbuga.entities.slideshow.SlideshowResponse;

import java.util.Arrays;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ApiTest {

    @Test
    void mockServerGet() {
        Comment[] comments = get("http://localhost:3000/comments")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .as(Comment[].class);

        System.out.println(comments[0].getBody());
    }

    @Test
    void mockServerPost() throws JsonProcessingException {
        Comment comment = new Comment();
        Body commentBody = new Body();
        ObjectMapper objectMapper = new ObjectMapper();

        commentBody.setTitle("New Test Comment Body 6");
        commentBody.setDescription("New Test Description 6");
        commentBody.setTags(Arrays.asList("Test Tag 16", "Test Tag 26"));

        comment.setId(6);
        comment.setBody(commentBody);
        comment.setPostId(1);

        String commentInJsonFormat = objectMapper.writeValueAsString(comment);

        Comment commentFromResponse = given()
                .header("Content-Type", "application/json")
                .body(commentInJsonFormat)
                .post("http://localhost:3000/comments")
                .then()
                .assertThat()
                .statusCode(201)
                .extract()
                .as(Comment.class);
    }


    @Test(description = "Test httpbin z POJO")
    void apiTestingWithPOJO() {
        // najłatwiej jest stworzyc POJO na bazie przykladowej odpowiedzi z serwera
        // z wykorzystaniem strony https://www.jsonschema2pojo.org/

        Slideshow slideshow = get("https://httpbin.org/json")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .as(SlideshowResponse.class)
                .getSlideshow();

        Assert.assertEquals(slideshow.getAuthor(), "Yours Truly");
        Assert.assertEquals(slideshow.getSlides().get(0).getTitle(), "Wake up to WonderWidgets!");
        Assert.assertEquals(slideshow.getDate(), "date of publication");
        Assert.assertEquals(slideshow.getTitle(), "Sample Slide Show");
    }

    @Test(description = "Test httpbin bez POJO")
    void apiTestingWithoutPOJO() {
        get("https://httpbin.org/json")
                .then()
                .assertThat()
                .statusCode(200)
                .body("slideshow.author", equalTo("Yours Truly"))
                .body("slideshow.date", equalTo("date of publication"))
                .body("slideshow.title", equalTo("Sample Slide Show"));
    }

}
