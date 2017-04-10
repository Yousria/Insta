package com.example;

import com.example.loginAPI.UserData;
import com.example.post.services.CommentServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by kokoghlanian on 09/04/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommentServiceImpl.class)
@UserData
@DataJpaTest
public class CommentTest {

    @Autowired
    CommentServiceImpl commentService;

    @Test
    public void should_find_comment(){
        Long id = 2L;
        assertThat(commentService.getCommentsByImageEntity(id).size()).isEqualTo(1);
    }
}
