package com.example.post;

import com.example.loginAPI.User;
import lombok.*;
import com.example.fileApi.ImageEntity;

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
    @Column(name="id")
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id")
    private User user;

    @Column
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id")
    private ImageEntity image;
}
