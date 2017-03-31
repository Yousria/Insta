package com.example.loginAPI.Service;

import com.example.loginAPI.User;
import com.example.loginAPI.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by bench on 08/03/2017.
 */
@RestController
@RequestMapping("/users")
public class UserServiceController {

    private final UserServices userServices;

    @Autowired
    public UserServiceController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping
    public List<UserDto> getUsers(){
        return userServices.getAllUsers();
    }

    @PostMapping
    public UserDto insertUser(@RequestBody User user){
        return userServices.createUser(user.getPseudo(), user.getEmail(), user.getPassword());
    }
    @GetMapping("/{pseudo}")
    public UserDto getUserByPseudo(@PathVariable String pseudo){
        return userServices.getUserByPseudo(pseudo);
    }

}
