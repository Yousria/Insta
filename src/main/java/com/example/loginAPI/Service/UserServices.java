package com.example.loginAPI.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.loginAPI.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.SecureRandom;
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
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserAdapter::toDto)
                .collect(toList());
    }

    public String createToken() {
        String token = "";
        SecureRandom random = new SecureRandom();
        try {
            Algorithm algorithm = Algorithm.HMAC256(new BigInteger(130, random).toString(32));
            token = JWT.create()
                    .withIssuer(new BigInteger(130, random).toString(32))
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            exception.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return token;
    }


    @Transactional
    public UserDto createUser(String pseudo, String email, String password, Role role) {
        User user = User.builder()
                .pseudo(pseudo)
                .email(email)
                .password(passwordEncoder.encode(password))
                .role(role)
                .token(createToken())
                .build();
        userRepository.save(user);
        return UserAdapter.toDto(user);
    }

    @Transactional
    public UserDto saveUser(UserDto dto) {
        User user = UserAdapter.toUser(dto);
        userRepository.save(user);
        return dto;
    }

    @Transactional(readOnly = true)
    public UserDto getUserByPseudo(String pseudo) {
        Optional<User> user = userRepository.findByPseudo(pseudo);
        return UserAdapter.toDto(user.get());
    }

    @Transactional(readOnly = true)
    public boolean verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build();
            DecodedJWT jwt = verifier.verify(token);
        } catch (UnsupportedEncodingException exception) {
            //UTF-8 encoding not supported
            return false;
        } catch (JWTVerificationException exception) {
            //Invalid signature/claims
            return false;
        }
        return true;
    }

    @Transactional(readOnly = true)
    public boolean verifyUser(String pseudo, String password){
        User user = userRepository.findByPseudo(pseudo).get();
        if(user == null){
            return false;
        }
        if(!password.equals(user.getPassword())){
            return false;
        }
        return true;
    }


}
