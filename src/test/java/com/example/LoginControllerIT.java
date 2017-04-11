package com.example;

import com.example.loginAPI.Role;
import com.example.loginAPI.Service.UserServices;
import com.jayway.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.jayway.restassured.RestAssured.given;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

/**
 * Created by bench on 11/04/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class LoginControllerIT {

    @LocalServerPort
    private int localServerPort;

    @Autowired
    private UserServices userServices;

    @Before
    public void init(){
        RestAssured.port = localServerPort;

        userServices.createUser("Yousria", "yous@yous.fr",
                "yousria1234", Role.USER);
        userServices.createUser("Vartan", "vartan@vartan.fr",
                "vartan1234", Role.USER);
        userServices.createUser("nico", "nico@nico.fr",
                "nico1", Role.USER);
    }

    @Test
    public void should_get_home_page(){
        given().log().all()
                .when().get("/home")
                .then().log().all()
                .contentType("text/html")
                .statusCode(200);
    }

    @Test
    public void should_get_welcome_page(){
        given().log().all()
                .when().get("/welcome")
                .then().log().all()
                .contentType("text/html")
                .statusCode(200);
    }

    @Test
    public void should_get_error_page(){
        given().log().all()
                .when().get("/error")
                .then().log().all()
                .contentType("text/html")
                .statusCode(200);
    }
    @Test
    public void should_get_registration_page(){
        given().log().all()
                .when().get("/registration")
                .then().log().all()
                .contentType("text/html")
                .statusCode(200);
    }
    @Test
    public void should_get_login_page(){
        given().log().all()
                .when().get("/login")
                .then().log().all()
                .contentType("text/html")
                .statusCode(200);
    }

    @Test
    public void should_return_to_registration_when_errors(){

    }

    @Test
    public void should_log_on_when_registered(){

    }

    @Test
    public void should_get_error_page_when_login_errors(){

    }

    @Test
    public void should_log_on(){

    }
}
