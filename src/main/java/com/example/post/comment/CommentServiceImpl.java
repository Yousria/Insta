package com.example.post.comment;

import com.example.loginAPI.Token;
import com.example.post.image.ImageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kokoghlanian on 07/03/2017.
 */
@Service
public class CommentServiceImpl implements CommentService {


    @Autowired
    CommentRepository commentRepository;

    @Override
    public void updateComment(Long id, String comment) {
        commentRepository.updateComment(comment,id);
    }

    @Override
    @Transactional
    public CommentDTO insertComment(String comment, Token token, ImageEntity imageEntity){
        CommentEntity commentEntity = CommentEntity.builder()
                .comment(comment)
                .image(imageEntity)
                .token(token)
                .build();
        commentRepository.save(commentEntity);

        return CommentAdapter.toCommentDTO(commentEntity);
    }

    public List<CommentDTO> getCommentsByImageEntity(ImageEntity imageEntity){

        Page<CommentEntity> commentList = commentRepository.findByImageEntity(imageEntity);
        List<CommentDTO> commentListDto = new ArrayList<>();
        for(CommentEntity comment : commentList)
        {
            commentListDto.add(CommentAdapter.toCommentDTO(comment));
        }
        return commentListDto;
    }
}
