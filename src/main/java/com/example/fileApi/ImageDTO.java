package com.example.fileApi;

import com.example.loginAPI.Token;
import com.example.post.CommentEntity;

import java.util.List;

/**
 * Created by Nicolas on 09/04/2017.
 */
public class ImageDTO {
    private Long id_image;

    private Long id_album;

    private String title;

    private Integer likescore;

    private Integer dislikescore;

    private List<CommentEntity> commentEntityList;
}
