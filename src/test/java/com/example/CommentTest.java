package com.example;

import com.example.loginAPI.User;
import com.example.loginAPI.UserData;
import com.example.post.services.CommentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by kokoghlanian on 09/04/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@UserData
@DataJpaTest
public class CommentTest {

    @Autowired
    CommentService commentService;
    @Test
    public void should_find_comment(){
        assertThat(commentService.getCommentsByImageEntity((long) 1).size()).isEqualTo(1);
    }

}
