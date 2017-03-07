package com.example.loginAPI;

import lombok.*;

import javax.persistence.*;

/**
 * Created by bench on 07/03/2017.
 */

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String pseudo;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

}
