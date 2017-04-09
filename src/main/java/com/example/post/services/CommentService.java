package com.example.post.services;

import com.example.loginAPI.User;
import com.example.post.CommentDTO;
import com.example.fileApi.ImageEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by kokoghlanian on 07/03/2017.
 */
public interface CommentService {

    CommentDTO updateComment(Long id, String comment);

    CommentDTO insertComment(String comment, User user , ImageEntity imageEntity);

    @Transactional
    List<CommentDTO> getCommentsByImageEntity(Long imageEntityId);
}
