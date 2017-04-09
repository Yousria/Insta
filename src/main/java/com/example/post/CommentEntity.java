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
@Table(name = "services")
public class CommentEntity {

    @Id
    @Column(name="id_comment")
    @GeneratedValue
    private Long id;


    /*@ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Token token;*/

    @Column
    private String comment;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id_image")
    private ImageEntity image;
}
