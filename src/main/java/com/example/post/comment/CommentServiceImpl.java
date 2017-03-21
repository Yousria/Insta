package com.example.post.comment;

import org.springframework.stereotype.Service;

/**
 * Created by kokoghlanian on 07/03/2017.
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Override
    public CommentEntity updateComment(CommentEntity commentEntity,String comment) {
        if(commentEntity == null)
            throw new IllegalArgumentException();

        commentEntity.setComment(comment);

        return commentEntity;
    }
}
