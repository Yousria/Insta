package com.example;

import com.example.loginAPI.User;
import com.example.loginAPI.UserData;
import com.example.loginAPI.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by bench on 07/03/2017.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes = UserRepository.class)
@UserData
public class UserRepositoryIT {

    @Autowired
    UserRepository userRepository;

    @Test
    public void should_find_by_pseudo(){
        String pseudoToSearch = "first";
        Optional<User> user = userRepository.findByPseudo(pseudoToSearch);
        assertThat(user.get().getId()).isEqualTo(1);
    }

    @Test
    public void should_find_by_id(){
        Long idToSearch = 2L;
        Optional<User> user = userRepository.findById(idToSearch);
        assertThat(user.get().getPseudo()).isEqualTo("second");
    }
}
