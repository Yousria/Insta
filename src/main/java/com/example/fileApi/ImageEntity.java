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
    @Column(name="id_image")
    @GeneratedValue
    private Long id_image;


    @Column
    private String title;

    @Column
    private Integer likescore;

    @Column
    private Integer dislikescore;

    @Column
    @ManyToOne(cascade= ALL)
    @JoinColumn(name = "album", referencedColumnName="id_album")
    private long id_album;

    @Column
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id_image")
    private List<CommentEntity> commentEntityList;

}