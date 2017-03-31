package com.example;

import com.example.loginAPI.User;
import com.example.loginAPI.UserAdapter;
import com.example.loginAPI.UserData;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

/**
 * Created by bench on 08/03/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@UserData
public class UserServiceControllerIT {

    @LocalServerPort
    private int localServerPort;

    @Before
    public void init(){
        RestAssured.port = localServerPort;
    }

    @Test
    public void should_get_all_users(){
        given().log().all()
                .when().get("users")
                .then().log().all()
                .statusCode(200)
                .body("$", hasSize(3));
    }

    @Test
    public void should_create_user(){
        User user = User.builder()
                .pseudo("Yous")
                .email("yous@yous.com")
                .password("youyou")
                .build();
        given().log().all()
                .contentType(JSON)
                .body(user)
                .when().post("users")
                .then().statusCode(200)
                .body("pseudo", equalTo("Yous"))
                .body("email", equalTo("yous@yous.com"))
                .body("password", equalTo("youyou"));
    }
    @Test
    public void should_get_user_by_pseudo(){
        given().log().all()
                .when().get("users/{pseudo}", "second")
                .then().log().all()
                .statusCode(200)
                .body("email", equalTo("second@second.com"))
                .body("password", equalTo("second"));
    }



}
