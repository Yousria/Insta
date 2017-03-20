package com.example.post.comment;

/**
 * Created by kokoghlanian on 06/03/2017.
 */
public class CommentAdapter {


    public static CommentDTO toCommentDTO(CommentEntity commentEntity){

        return CommentDTO.builder()
             .id(commentEntity.getId())
             .token(commentEntity.getToken())
             .comment(commentEntity.getComment())
             .image(commentEntity.getImage()).build();
    }

    public static CommentEntity toComment(CommentDTO commentDTO) {

        return CommentEntity.builder()
                .id(commentDTO.getId())
                .token(commentDTO.getToken())
                .comment(commentDTO.getComment())
                .image(commentDTO.getImage())
                .build();
    }

}
