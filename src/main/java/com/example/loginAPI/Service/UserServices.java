package com.example.loginAPI.Service;

import com.example.loginAPI.*;
import com.sun.javafx.fxml.expression.Expression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.token.DefaultToken;
import org.springframework.security.core.token.Token;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;
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
    public UserDto createUser(String pseudo, String email, String password, Role role){
        SecureRandom random = new SecureRandom();
        String token = new BigInteger(256, random).toString(32);
        User user = User.builder()
                .pseudo(pseudo)
                .email(email)
                .password(passwordEncoder.encode(password))
                .role(role)
                .token(token)
                .build();
        userRepository.save(user);
        return UserAdapter.toDto(user);
    }

    @Transactional
    public UserDto saveUser(UserDto dto){
        User user = UserAdapter.toUser(dto);
        user.setRole(Role.USER);
        userRepository.save(user);
        return dto;
    }

    @Transactional(readOnly = true)
    public UserDto getUserByPseudo(String pseudo){
        Optional<User> user = userRepository.findByPseudo(pseudo);
        return UserAdapter.toDto(user.get());
    }


}
