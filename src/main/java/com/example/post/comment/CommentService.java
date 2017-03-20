package com.example.post.comment;


import com.example.loginAPI.Token;
import com.example.post.image.ImageEntity;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by kokoghlanian on 07/03/2017.
 */
public interface CommentService {

    void updateComment(Long id, String comment);

    CommentDTO insertComment(String comment, Token token, ImageEntity imageEntity);
}
