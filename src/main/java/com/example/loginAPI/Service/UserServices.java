package com.example.loginAPI.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.loginAPI.*;
import com.example.post.social.friend.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * Created by bench on 07/03/2017.
 */
@Service
public class UserServices {


    final UserRepository userRepository;
    final BCryptPasswordEncoder passwordEncoder;
    final FriendService friendService;



    @Autowired
    public UserServices(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, FriendService friendService, DataSource dataSource) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.friendService = friendService;
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
        if(userRepository.findByPseudo(pseudo).isPresent()){
            return null;
        }else {
            User user = User.builder()
                    .pseudo(pseudo)
                    .email(email)
                    .password(passwordEncoder.encode(password))
                    .role(role)
                    .token(createToken(pseudo, password))
                    .build();
            userRepository.save(user);
            User u = userRepository.findByPseudo(pseudo).get();
            friendService.addUser(u.getId(), pseudo);
            return UserAdapter.toDto(user);
        }
    }

    @Transactional
    public UserDto saveUser(User user) {
        user.setToken(createToken(user.getPseudo(), user.getPassword()));
        userRepository.save(user);
        return UserAdapter.toDto(user);
    }

    @Transactional(readOnly = true)
    public User getUserByPseudo(String pseudo) {
        Optional<User> user = userRepository.findByPseudo(pseudo);
        if(user.isPresent()) {
            return user.get();
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

    @Transactional
    public void removeUser(Long id){
        Optional<User> u = userRepository.findById(id);
        if(u.isPresent()){
            userRepository.delete(u.get());
            friendService.removeUser(id);
        }
    }


}
