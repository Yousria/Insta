package com.example.fileApi;

import com.example.fileApi.stockage.Album;
import com.example.post.CommentEntity;
import lombok.*;

import java.util.List;

/**
 * Created by Nicolas on 09/04/2017.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageDTO {
    private Long id_image;

    private Album album;

    private String title;

    private Integer likescore;

    private Integer dislikescore;

    private List<CommentEntity> commentEntityList;
}
