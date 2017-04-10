package com.example.fileApi;

import com.example.loginAPI.User;
import com.example.post.CommentEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

/**
 * Created by Nicolas on 09/04/2017.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "album")
public class AlbumEntity {
    @Id
    @Column
    @GeneratedValue
    private Long id;


    @Column
    private String title;


    @ManyToOne(cascade = ALL)
    @JoinColumn(name = "users",referencedColumnName = "id")
    private User user;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "album")
    private List<ImageEntity> imageEntityList;
}
