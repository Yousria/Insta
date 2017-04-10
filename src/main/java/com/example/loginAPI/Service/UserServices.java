package com.example.loginAPI.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
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

    public String createToken(String pseudo, String password ){
        String token = "";
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            token = JWT.create()
                    .withClaim("admin", false)
                    .withClaim("username", pseudo)
                    .withClaim("password", password)
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
                .token(createToken(pseudo, password))
                .build();
        userRepository.save(user);
        return UserAdapter.toDto(user);
    }

    @Transactional
    public UserDto saveUser(User user) {
        user.setToken(createToken(user.getPseudo(), user.getPassword()));
        userRepository.save(user);
        return UserAdapter.toDto(user);
    }

    @Transactional(readOnly = true)
    public UserDto getUserByPseudo(String pseudo) {
        Optional<User> user = userRepository.findByPseudo(pseudo);
        if(user.isPresent()) {
            return UserAdapter.toDto(user.get());
        }else{
            return null;
        }
    }

    @Transactional(readOnly = true)
    public boolean verifyToken(String token) {
        try{
            DecodedJWT decodedJWT = JWT.decode(token);
            String pseudo = decodedJWT.getClaim("username").asString();
            String password = decodedJWT.getClaim("password").asString();
            System.out.println(pseudo + " " + password);
            if(!verifyUser(pseudo, password)){
                return false;
            }
        }catch(JWTDecodeException e){
            e.printStackTrace();
        }
        return true;
    }

    @Transactional(readOnly = true)
    public boolean verifyUser(String pseudo, String password){
        Optional<User> user = userRepository.findByPseudo(pseudo);
        if(!user.isPresent()) {
            return false;
        }else if(!passwordEncoder.matches(password, user.get().getPassword())){
            return false;
        }
        return true;
    }


}
