package com.example;

import com.example.fileApi.AlbumAdapter;
import com.example.fileApi.AlbumEntity;
import com.example.fileApi.ImageAdapter;
import com.example.fileApi.ImageEntity;
import com.example.fileApi.services.AlbumService;
import com.example.fileApi.services.ImageService;
import com.example.loginAPI.User;
import com.example.post.CommentDTO;
import com.example.post.CommentRepositoryImpl;
import com.example.post.services.CommentService;
import com.jayway.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.example.loginAPI.Role.USER;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

/**
 * Created by kokoghlanian on 11/04/2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class CommentControllerTest {

    @LocalServerPort
    private int localServerPort;

    @Autowired
    CommentService commentService;
    @Autowired
    AlbumService albumService;
    @Autowired
    ImageService imageService;
    @Autowired
    CommentRepositoryImpl commentRepository;

    ImageEntity imageEntity;
    User user;
    CommentDTO commentOne;
    CommentDTO commentTwo;
    CommentDTO commentThree;



    @Before
    public void init(){
        RestAssured.port = localServerPort;
        user = User.builder()
                .pseudo("pseudo")
                .email("mail@mail.fr")
                .password("monmp")
                .role(USER)
                .build();

        AlbumEntity albumEntity =
                AlbumAdapter.toAlbumEntity(albumService.insertAlbum("bonjour", user));

        imageEntity =
                ImageAdapter.toImageEntity(imageService.insertImage("coucou",
                        albumEntity,
                        null));


        commentOne =  commentService.insertComment("BONJOURBONJOUR",user,imageEntity);
        commentTwo =  commentService.insertComment("coucou",user,imageEntity);
        commentThree =  commentService.insertComment("salut",user,imageEntity);

    }


    @Test
    public void should_insert_comment(){

    }

    @Test
    public void should_get_comment_by_image_entity(){
        given()
                .log().all()
                .when()
                .get("/comments/{idImageEntity}",1)
                .then()
                .log().all()
                .statusCode(200)
                .body("$", hasSize(3));
    }

    @Test
    public void should_get_comment_by_user(){
        given()
                .log().all()
                .when()
                .get("/comments/{idUser}",1)
                .then()
                .log().all()
                .statusCode(200)
                .body("$", hasSize(3));
    }

    @Test
    public void should_delete_comment(){
        given()
                .log().all()
                .when()
                .get("/comments/delete/{idComment}",1)
                .then()
                .log().all()
                .statusCode(200)
                .body("$", hasSize(2));
    }

}
