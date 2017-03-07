package com.example.loginAPI;

import lombok.*;

import javax.persistence.*;

/**
 * Created by bench on 07/03/2017.
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@Table(name = "tokens")
public class Token {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    @JoinColumn(referencedColumnName = "pseudo", nullable = false)
    private String pseudo;

    @Column
    private String token;
}
