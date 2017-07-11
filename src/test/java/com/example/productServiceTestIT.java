package com.example;

import com.example.loginAPI.UserData;
import com.jayway.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.assertj.core.api.Java6Assertions.assertThat;
/**
 * Created by Nicolas_Travail on 15/04/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@UserData
public class productServiceTestIT {

    @LocalServerPort
    private int localServerPort;

    @Before
    public void initialize_data(){


        RestAssured.port=localServerPort;

    }




       @Test
        public void should_add_album(){
            given().log().all()
                    .when().post("/albums/addAlbum?albumName=albumajoute&pseudo=second")
                    .then().statusCode(200).body("title",equalTo("albumajoute"));
        }

        @Test
        public void should_get_2_albums(){
            given().log().all()
                    .when().get("/albums/getAlbums/{id}",1)
                    .then().log().all()
                    .statusCode(200).body("$",hasSize(2));
        }
    @Test
    public void should_get_0_album(){
        given().log().all()
                .when().get("/albums/getAlbums/{id}",3)
                .then().log().all()
                .statusCode(200).body("$",hasSize(0));
    }

        @Test
        public void should_updated_title(){
            given().log().all()
                    .when().post("/albums/updateAlbumTitle?albumName=albumtest&pseudo=first&newName=newName")
                    .then().log().all()
                    .statusCode(200).body(equalTo("1"));
        }
    @Test
    public void should_not_updated_title(){
        given().log().all()
                .when().post("/albums/updateAlbumTitle?albumName=albumpourri&pseudo=first&newName=newName")
                .then().log().all()
                .statusCode(200).body(equalTo("0"));
    }



}
