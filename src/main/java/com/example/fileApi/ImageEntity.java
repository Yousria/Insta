package com.example.fileApi;

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
@Table(name = "image")
public class ImageEntity {

    @Id
    @Column
    @GeneratedValue
    private Long id;


    @Column
    private String title;

    @Column
    private Integer likescore;

    @Column
    private Integer dislikescore;

    @Column(length = 255000)
    private byte[] datas;

    @ManyToOne(cascade =CascadeType.DETACH)
    @JoinColumn(name = "album_photo")
    protected AlbumEntity album;

   /* @OneToMany(fetch = FetchType.LAZY, mappedBy = "image")
    private List<CommentEntity> commentEntityList;*/

}