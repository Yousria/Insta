package com.example.post.comment;

import com.example.post.CommentEntity;

/**
 * Created by kokoghlanian on 07/03/2017.
 */
public interface CommentService {
    CommentEntity updateComment(CommentEntity commentEntity, String comment);
}
