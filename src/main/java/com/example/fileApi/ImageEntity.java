package com.example.fileApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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

    @Column(length = 255000000)
    private byte[] datas;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "product",referencedColumnName = "id")
    protected ProductEntity product;

   /* @OneToMany(fetch = FetchType.LAZY, mappedBy = "image")
    private List<CommentEntity> commentEntityList;*/

}