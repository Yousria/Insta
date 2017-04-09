package com.example.post;

import com.example.loginAPI.Token;
import lombok.*;

import javax.persistence.*;

/**
 * Created by kokoghlanian on 06/03/2017.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "image")
public class ImageEntity {

    @Id
    @Column(name="id_image")
    @GeneratedValue
    private Long id;

    /*@ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Token token;*/

    @Column
    private String title;

    @Column
    private Integer likescore;

    @Column
    private Integer dislikescore;
}
