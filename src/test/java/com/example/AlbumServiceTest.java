package com.example;

import com.example.fileApi.*;
import com.example.fileApi.services.AlbumServiceImpl;
import com.example.fileApi.services.ImageServiceImpl;
import com.example.loginAPI.User;
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
 * Created by Nicolas_Travail on 11/04/2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AlbumServiceImpl.class, ImageServiceImpl.class})
@DataJpaTest
public class AlbumServiceTest {

    @Autowired
    AlbumServiceImpl albumService;
    @Autowired
    ImageServiceImpl imageService;

    User user;
    AlbumEntity album1;
    AlbumEntity album2;

    @Before
    public void initialize_data(){

        user = User.builder()
                .pseudo("pseudo")
                .email("mail@mail.fr")
                .password("monmp")
                .role(USER)
                .build();

         album1 =
                AlbumAdapter.toAlbumEntity(albumService.insertAlbum("album1", user));
        album2=
                AlbumAdapter.toAlbumEntity(albumService.insertAlbum("album2", user));

    }

    @Test
    public void should_return_album2_with_id(){
        AlbumDTO albumDTOResult= albumService.findById(album2.getId());
        assertThat(albumDTOResult.getId()).isEqualTo(album2.getId());
        assertThat(albumDTOResult.getTitle()).isEqualTo(album2.getTitle());
    }
    @Test
    public void should_return_album2_with_title(){
        AlbumDTO albumDTOResult= albumService.findByTitle(album2.getTitle());
        assertThat(albumDTOResult.getId()).isEqualTo(album2.getId());
        assertThat(albumDTOResult.getTitle()).isEqualTo(album2.getTitle());
    }
    @Test
    public void should_return_album2_with_title_and_pseudo(){
        AlbumDTO albumDTOResult= albumService.findByTitleAndPseudo(album2.getTitle(),user.getPseudo());

        assertThat(albumDTOResult.getId()).isEqualTo(album2.getId());
        assertThat(albumDTOResult.getTitle()).isEqualTo(album2.getTitle());
    }
    @Test
    public void find_all_by_user_should_return_the_two_albums(){
        List<AlbumEntity> albumEntities = AlbumAdapter.listToAlbumEntity(albumService.findAllByUser(user.getPseudo()));
       assertThat(albumEntities.size()).isEqualTo(2);
       assertThat(albumEntities).contains(album1);
        assertThat(albumEntities).contains(album2);
    }
}

