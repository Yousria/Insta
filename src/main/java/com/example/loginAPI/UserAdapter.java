package com.example.loginAPI;




/**
 * Created by bench on 07/03/2017.
 */

/**
 * the password is not instantiated,
 * it will be encoded in the service
 */
public class UserAdapter {

    public static UserDto toDto(User user){
        return UserDto.builder()
                .email(user.getEmail())
                .pseudo(user.getPseudo())
                .build();
    }

    public static User toUser(UserDto dto){
        return User.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();
    }
}
