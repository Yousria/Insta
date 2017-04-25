package com.example.fileApi;

import com.example.loginAPI.User;
import com.example.post.CommentEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.*;

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


    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "users",referencedColumnName = "id")
    private User user;
    @JsonIgnore
  @OneToMany(fetch = LAZY, mappedBy = "album")
    private List<ImageEntity> imageEntityList;

    @Override
    public boolean equals(Object obj) {
        AlbumEntity albumEntity = (AlbumEntity) obj;
        return albumEntity.getId().equals(id)&&
                albumEntity.getTitle().equals(title);
    }
}
