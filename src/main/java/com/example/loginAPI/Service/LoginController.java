package com.example.loginAPI.Service;

import com.example.loginAPI.User;
import com.example.loginAPI.UserAdapter;
import com.example.loginAPI.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by bench on 27/03/2017.
 */
@Controller
public class LoginController {

    private final UserServices userServices;
    private final SecurityService securityService;
    private final UserValidator userValidator;

    public LoginController(UserServices userServices, SecurityService securityService, UserValidator userValidator) {
        this.userServices = userServices;
        this.securityService = securityService;
        this.userValidator = userValidator;
    }

    @GetMapping(value = "/login", produces = "text/html")
    public String getLoginPage(Model model){
        model.addAttribute("hello", "coucou");
        return "loginPage";
    }

   /* @GetMapping("/login")
    @ResponseBody
    public String login(@RequestParam("pseudo") String pseudo,
                        @RequestParam("password") String password){
        UserDto user = userServices.getUserByPseudo(pseudo);
        if(user != null){
            if(password.equals(user.getPassword())){
                return "youpi je suis connecté";
            }
        }
        return "erreur login blabla";
    }*/

    @RequestMapping(value = "/registration", method = GET)
    public String registration(Model model){
        model.addAttribute("userForm", new User());
        return "registrationPage";
    }

    /*@RequestMapping(value = "/registration", method = POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult,
                               Model model){
        userValidator.validate(userForm, bindingResult);
        if(bindingResult.hasErrors()){
            return "registration";
        }
        userServices.saveUser(UserAdapter.toDto(userForm));
        securityService.autoLogin(userForm.getPseudo(), userForm.getPassword());
        return "redirect::/welcome";
    }*/
}
