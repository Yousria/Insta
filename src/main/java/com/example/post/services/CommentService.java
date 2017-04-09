package com.example.post.services;

import com.example.loginAPI.User;
import com.example.post.CommentDTO;
import com.example.fileApi.ImageEntity;


/**
 * Created by kokoghlanian on 07/03/2017.
 */
public interface CommentService {

    void updateComment(Long id, String comment);

    CommentDTO insertComment(String comment, User user , ImageEntity imageEntity);
    //List<CommentDTO> getCommentsByImageEntity(ImageEntity imageEntity);
}
