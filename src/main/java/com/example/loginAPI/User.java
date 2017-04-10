package com.example.loginAPI;

import com.example.fileApi.AlbumEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.List;

import static javax.persistence.EnumType.STRING;

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
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String pseudo;

    @Column
    @NotNull
    private String email;

    @Column
    @NotNull
    private String password;

    @Column
    @Enumerated(STRING)
    private Role role;

    @Column
    private String token;
}
