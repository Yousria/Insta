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
    private final SecurityService securityService;
    private final UserValidator userValidator;

    @Autowired
    public UserServiceController(UserServices userServices, SecurityService securityService, UserValidator userValidator) {
        this.userServices = userServices;
        this.securityService = securityService;
        this.userValidator = userValidator;
    }

    @GetMapping
    public List<UserDto> getUsers(){
        return userServices.getAllUsers();
    }

    @PostMapping
    public UserDto insertUser(@RequestBody User user){
        return userServices.createUser(user.getPseudo(), user.getEmail(), user.getPassword(), user.getRole());
    }
    @GetMapping("/{pseudo}")
    public UserDto getUserByPseudo(@PathVariable String pseudo){
        return userServices.getUserByPseudo(pseudo);
    }

    @PostMapping("/token")
    public String verifyToken(@RequestBody User user){
        return String.valueOf(userServices.verifyToken(user.getToken()));
    }
    @GetMapping("/verify")
    public String verifyUser(@RequestParam("pseudo") String pseudo,
                             @RequestParam("password") String password){
        if(userServices.verifyUser(pseudo, password) == false){
            return "Error in username or password";
        }else{
            return "user verified!";
        }
    }
}
