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
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;

import static com.example.loginAPI.Role.USER;
import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * @author Nicolas Sirac
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ProductServiceImpl.class, ImageServiceImpl.class})
@DataJpaTest
public class productServiceTest {

    @Autowired
    private
    ProductServiceImpl productService;
    @Autowired
    ImageServiceImpl imageService;
    @Autowired
    private
    UserRepository userRepository;

    private User user;
    private User user1;
    private ProductEntity album1;
    private ProductEntity album2;
    private ProductEntity album3;

    @Before
    public void initialize_data(){

        user = User.builder()
                .pseudo("pseudo")
                .email("mail@mail.fr")
                .password("monmp")
                .role(USER)
                .build();
        user1 = User.builder()
                .pseudo("pseudo2")
                .email("mail@mail.fr")
                .password("monmp")
                .role(USER)
                .build();

        userRepository.save(user);
        userRepository.save(user1);
        album1 = ProductAdapter.toProductEntity(productService.insertAlbum("album1", user));
        album2 = ProductAdapter.toProductEntity(productService.insertAlbum("album2", user));
        album3 = ProductAdapter.toProductEntity(productService.insertAlbum("album3", user1));
    }

    @Test
    public void should_return_album2_with_id(){
        ProductDTO albumDTOResult= productService.findById(album2.getId());
        assertThat(albumDTOResult.getId()).isEqualTo(album2.getId());
        assertThat(albumDTOResult.getTitle()).isEqualTo(album2.getTitle());
    }
    @Test
    public void should_return_album2_with_title(){
        ProductDTO albumDTOResult= productService.findByTitle(album2.getTitle());
        assertThat(albumDTOResult.getId()).isEqualTo(album2.getId());
        assertThat(albumDTOResult.getTitle()).isEqualTo(album2.getTitle());
    }
    @Test
    public void should_return_album2_with_title_and_pseudo(){
        ProductDTO albumDTOResult= productService.findByTitleAndPseudo(album2.getTitle(),user.getPseudo());

        assertThat(albumDTOResult.getId()).isEqualTo(album2.getId());
        assertThat(albumDTOResult.getTitle()).isEqualTo(album2.getTitle());
    }
    @Test
    public void find_all_by_user_should_return_the_two_albums(){
        List<ProductEntity> albumEntities = ProductAdapter.listToAlbumEntity(productService.findAllByUser(user.getId()));
       assertThat(productService.findAllByUser(user.getId()).size()).isEqualTo(2);
     assertThat(albumEntities.get(0).equals(album1)).isTrue();
       assertThat(albumEntities.get(1).equals(album2)).isTrue();
    }
}

