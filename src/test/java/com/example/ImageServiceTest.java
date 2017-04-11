package com.example;

import com.example.fileApi.*;
import com.example.fileApi.services.AlbumService;
import com.example.fileApi.services.AlbumServiceImpl;
import com.example.fileApi.services.ImageService;
import com.example.fileApi.services.ImageServiceImpl;
import com.example.loginAPI.Role;
import com.example.loginAPI.User;
import com.example.post.CommentAdapter;
import com.example.post.CommentDTO;
import com.example.post.CommentRepositoryImpl;
import com.example.post.services.CommentServiceImpl;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

import static com.example.loginAPI.Role.USER;
import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by Nicolas_Travail on 11/04/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AlbumServiceImpl.class, ImageServiceImpl.class})
@DataJpaTest
public class ImageServiceTest {

    @Autowired
    AlbumServiceImpl albumService;
    @Autowired
    ImageServiceImpl imageService;

    ImageEntity imageEntity;
    ImageEntity imageEntity2;
    User user;

    @Before
    public void initialize_data(){

        user = User.builder()
                .pseudo("pseudo")
                .email("mail@mail.fr")
                .password("monmp")
                .role(USER)
                .build();

        AlbumEntity albumEntity =
                AlbumAdapter.toAlbumEntity(albumService.insertAlbum("bonjour", user));

        try {
            imageEntity =
                    ImageAdapter.toImageEntity(imageService.insertImage("image1",
                            albumEntity,
                            new MockMultipartFile("file 1",new FileInputStream("src/test/resources/image1.jpg")).getBytes()));
            imageService.insertImage(imageEntity.getTitle(),imageEntity.getAlbum(),imageEntity.getDatas());
            imageEntity2 =
                    ImageAdapter.toImageEntity(imageService.insertImage("image2",
                            albumEntity,
                            new MockMultipartFile("file 2",new FileInputStream("src/test/resources/image2.jpg")).getBytes()));
            imageService.insertImage(imageEntity2.getTitle(),imageEntity2.getAlbum(),imageEntity2.getDatas());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }




    @Test
    public void should_return_image1_with_id(){
        ImageDTO imageEntityResult= imageService.findById(imageEntity.getId());
        assertThat(imageEntityResult.getId()).isEqualTo(imageEntity.getId());
        assertThat(imageEntityResult.getTitle()).isEqualTo(imageEntity.getTitle());
        assertThat(Arrays.equals( imageEntityResult.getDatas(),imageEntity.getDatas()));
    }
    @Test
    public void should_return_image1_with_title(){
        ImageDTO imageEntityResult= imageService.findByTitle(imageEntity2.getTitle());
        assertThat(imageEntityResult.getId()).isEqualTo(imageEntity2.getId());
        assertThat(imageEntityResult.getTitle()).isEqualTo(imageEntity2.getTitle());
        assertThat(Arrays.equals( imageEntityResult.getDatas(),imageEntity2.getDatas()));
    }
    @Test
    public void should_update_dislike(){
        imageService.updateDislike(imageEntity);
        ImageDTO imageDTO= imageService.findById(imageEntity.getId());
        assertThat(imageDTO.getDislikescore().equals(imageEntity.getDislikescore()+1));
    }
    @Test
    public void should_update_Like(){
        imageService.updateLike(imageEntity);
        ImageDTO imageDTO= imageService.findById(imageEntity.getId());
        assertThat(imageDTO.getLikescore().equals(imageEntity.getLikescore()+1));
    }
    @Test
    public void should_update_Title(){
        imageService.updateTitle(imageEntity2,"newtitle");
        ImageDTO imageDTO= imageService.findById(imageEntity2.getId());
        assertThat(imageDTO.getTitle().equals("newtitle"));
    }



}
