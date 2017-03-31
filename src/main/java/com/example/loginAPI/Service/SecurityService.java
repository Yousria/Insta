package com.example.loginAPI.Service;

/**
 * Created by bench on 08/03/2017.
 */
public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String pseudo, String password);
}
