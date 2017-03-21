package com.example.loginAPI.Service;

import com.example.loginAPI.User;
import com.example.loginAPI.UserAdapter;
import com.example.loginAPI.UserDto;
import com.example.loginAPI.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * Created by bench on 07/03/2017.
 */
@Service
public class UserServices {


    final UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<UserDto> getAllUsers(){
        return userRepository.findAll()
                .stream()
                .map(UserAdapter::toDto)
                .collect(toList());
    }

    @Transactional
    public UserDto createUser(String pseudo, String email, String password){
        User user = User.builder()
                .pseudo(pseudo)
                .email(email)
                .password(passwordEncoder.encode(password))
                .build();
        userRepository.save(user);
        return UserAdapter.toDto(user);
    }

    @Transactional(readOnly = true)
    public UserDto getUserByPseudo(String pseudo){
        Optional<User> user = userRepository.findByPseudo(pseudo);
        return UserAdapter.toDto(user.get());
    }


}
