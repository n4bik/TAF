package pl.tomaszbuga.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pl.tomaszbuga.api.entities.apiServer.Body;
import pl.tomaszbuga.api.entities.apiServer.Comment;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;

public class ApiTest {
    final private static String COMMENTS_API_URL = "http://localhost:3000/comments/";
    private SoftAssert softAssert;
    @BeforeMethod
    void setup() {
        softAssert = new SoftAssert();
    }

    @Test(groups = "Regression")
    void mockServerGet() {
        Comment[] comments = get(COMMENTS_API_URL)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(Comment[].class);

        softAssert.assertEquals(comments[0].getId().toString(), "1");
        softAssert.assertEquals(comments[0].getPostId().toString(), "1");
        softAssert.assertEquals(comments[0].getBody().getTitle(), "some title");
        softAssert.assertEquals(comments[0].getBody().getDescription(), "some description");

        softAssert.assertEquals(comments[1].getId().toString(), "2");
        softAssert.assertEquals(comments[1].getPostId().toString(), "1");
        softAssert.assertEquals(comments[1].getBody().getTitle(), "some title 2");
        softAssert.assertEquals(comments[1].getBody().getDescription(), "some description 2");

        softAssert.assertAll();
    }

    @Test
    void mockServerPost() throws JsonProcessingException {
        String commentTitle = "New Test Comment Body 6";
        String commentDescription = "New Test Description 6";
        List<String> commentTags = Arrays.asList("Test Tag 16", "Test Tag 26");
        int commentId = 1337;
        int postId = 1;

        Comment expectedComment = generateNewComment(commentTitle, commentDescription, commentTags, commentId, postId);
        Comment commentFromResponse = addNewComment(expectedComment);

        removeCommentInDatabaseById(commentId);
        assertEquals(commentFromResponse, expectedComment);
    }

    private static Comment generateNewComment(String title, String description, List<String> tags, int commentId, int postId) {
        Comment expectedComment = new Comment();
        Body commentBody = new Body();

        commentBody.setTitle(title);
        commentBody.setDescription(description);
        commentBody.setTags(tags);

        expectedComment.setId(commentId);
        expectedComment.setBody(commentBody);
        expectedComment.setPostId(postId);

        return expectedComment;
    }

    private static Comment addNewComment(Comment expectedComment) throws JsonProcessingException {
        return given()
                // .log().all() // uncomment for debugging
                .header("Content-Type", "application/json")
                .body(convertCommentToJSON(expectedComment))
                .post(COMMENTS_API_URL)
                .then()
                .assertThat()
                .statusCode(201)
                .extract()
                .as(Comment.class);
    }

    private static String convertCommentToJSON(Comment expectedComment) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(expectedComment);
    }

    private void removeCommentInDatabaseById(int commentId) {
        given()
                // .log().all() // uncomment for debugging
                .header("Content-Type", "application/json")
                .delete(COMMENTS_API_URL + commentId)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }
}
