package com.example.post.comment;

import com.example.loginAPI.Token;
import com.example.post.image.ImageEntity;
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
@Table(name = "comment")
public class CommentEntity {

    @Id
    @Column(name="id_comment")
    @GeneratedValue
    private Long id;

    @ManyToOne
    @Column
    private Token token;

    @Column
    private String comment;

    @ManyToOne
    @Column
    private ImageEntity image;
}
