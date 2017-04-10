package com.example;

import com.example.fileApi.*;
import com.example.fileApi.services.AlbumServiceImpl;
import com.example.fileApi.services.ImageServiceImpl;
import com.example.loginAPI.Service.UserServices;
import com.example.loginAPI.User;
import com.example.loginAPI.UserAdapter;
import com.example.loginAPI.UserData;
import com.example.post.CommentDTO;
import com.example.post.services.CommentServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static com.example.loginAPI.Role.USER;
import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by kokoghlanian on 09/04/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CommentServiceImpl.class,AlbumServiceImpl.class, ImageServiceImpl.class})
@DataJpaTest
public class CommentTest {

    @Autowired
    CommentServiceImpl commentService;
    @Autowired
    AlbumServiceImpl albumService;
    @Autowired
    ImageServiceImpl imageService;


    @Test
    public void should_find_comment(){

        User user = User.builder().pseudo("pseudo").email("mail@mail.fr").password("monmp").role(USER).build();
        albumService.insertAlbum("bonjour", user);
        ImageDTO imageEntity =  imageService.insertImage("coucou", AlbumAdapter.toAlbumEntity(albumService.findByTitle("bonjour")),null);
        CommentDTO comment =  commentService.insertComment("BONJOURBONJOUR",user,ImageAdapter.toImageEntity(imageEntity));
        assertThat(comment.getComment()).isEqualTo("BONJOURBONJOUR");
        Long id = 1L;
        assertThat(commentService.getCommentsByImageEntity(id).size()).isEqualTo(1);
    }

    @Test
    public void should_insert_comment(){

        User user = User.builder().pseudo("pseudo").email("mail@mail.fr").password("monmp").role(USER).build();
        albumService.insertAlbum("bonjour", user);
        ImageDTO imageEntity =  imageService.insertImage("coucou", AlbumAdapter.toAlbumEntity(albumService.findByTitle("bonjour")),null);
        CommentDTO comment =  commentService.insertComment("BONJOURBONJOUR",user,ImageAdapter.toImageEntity(imageEntity));
        assertThat(comment.getComment()).isEqualTo("BONJOURBONJOUR");
    }

    @Test
    public void should_update_comment(){

        User user = User.builder().pseudo("pseudo").email("mail@mail.fr").password("monmp").role(USER).build();
        albumService.insertAlbum("bonjour", user);
        ImageDTO imageEntity =  imageService.insertImage("coucou", AlbumAdapter.toAlbumEntity(albumService.findByTitle("bonjour")),null);
        commentService.insertComment("BONJOURBONJOUR",user,ImageAdapter.toImageEntity(imageEntity));
        Long id = 1L;
        commentService.updateComment(id,"SALUTTOI");
        assertThat(commentService.getCommentsByImageEntity(id).get(0).getComment()).isEqualTo("SALUTTOI");
    }
}
