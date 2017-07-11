package com.example;

import com.example.fileApi.*;
import com.example.fileApi.services.ProductServiceImpl;
import com.example.fileApi.services.ImageServiceImpl;
import com.example.loginAPI.User;
import com.example.loginAPI.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.example.loginAPI.Role.USER;
import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by Nicolas_Travail on 11/04/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ProductServiceImpl.class, ImageServiceImpl.class})
@DataJpaTest
public class ImageServiceTest {

    @Autowired
    ProductServiceImpl productService;
    @Autowired
    ImageServiceImpl imageService;
    @Autowired
    UserRepository userRepository;


    ImageEntity imageEntity;
    ImageEntity imageEntity2;
    ImageEntity imageEntity3;
    User user;
    ProductEntity productEntity;
    ProductEntity productEntity2;
    @Before
    public void initialize_data(){

        user = User.builder()
                .pseudo("pseudo")
                .email("mail@mail.fr")
                .password("monmp")
                .role(USER)
                .build();
        userRepository.save(user);
       productEntity =
                ProductAdapter.toProductEntity(productService.insertAlbum("bonjour", user));
        productEntity2 =
                ProductAdapter.toProductEntity(productService.insertAlbum("bonjour2", user));

        try {
            imageEntity =
                    ImageAdapter.toImageEntity(imageService.insertImage("image1",
                            productEntity,
                            new MockMultipartFile("file 1",new FileInputStream("src/test/resources/image1.jpg")).getBytes()));
            imageEntity2 =
                    ImageAdapter.toImageEntity(imageService.insertImage("image2",
                            productEntity,
                            new MockMultipartFile("file 2",new FileInputStream("src/test/resources/image2.jpg")).getBytes()));
            imageEntity3 =
                    ImageAdapter.toImageEntity(imageService.insertImage("image2",
                            productEntity2,
                            new MockMultipartFile("file 2",new FileInputStream("src/test/resources/image2.jpg")).getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }




   @Test

    public void should_return_image1_with_id(){
        ImageDTO imageDTOResult= imageService.findById(imageEntity.getId());
        assertThat(imageDTOResult.getId()).isEqualTo(imageEntity.getId());
        assertThat(imageDTOResult.getTitle()).isEqualTo(imageEntity.getTitle());
        assertThat(Arrays.equals( imageDTOResult.getDatas(),imageEntity.getDatas()));
    }
    @Test
    public void should_return_image1_with_title(){
        ImageDTO imageDTOResult= imageService.findByTitle(imageEntity2.getTitle());
        assertThat(imageDTOResult.getId()).isEqualTo(imageEntity2.getId());
        assertThat(imageDTOResult.getTitle()).isEqualTo(imageEntity2.getTitle());
        assertThat(Arrays.equals( imageDTOResult.getDatas(),imageEntity2.getDatas()));
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

    @Test
    public void should_return_the_two_image_by_album(){
        List<ImageDTO> imageDTOList =imageService.getByProduct(productEntity.getId());
        assertThat(imageDTOList.size()).isEqualTo(2);
    }



}
