package com.example.post.services;


import com.example.loginAPI.Token;
import com.example.post.CommentDTO;
import com.example.post.ImageEntity;

import java.util.List;

/**
 * Created by kokoghlanian on 07/03/2017.
 */
public interface CommentService {

    void updateComment(Long id, String comment);

    CommentDTO insertComment(String comment, Token token, ImageEntity imageEntity);
    //List<CommentDTO> getCommentsByImageEntity(ImageEntity imageEntity);
}
