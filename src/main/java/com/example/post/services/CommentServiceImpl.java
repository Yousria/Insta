package com.example.post.services;

import com.example.loginAPI.Token;
import com.example.loginAPI.User;
import com.example.post.CommentAdapter;
import com.example.post.CommentDTO;
import com.example.post.CommentEntity;
import com.example.post.CommentRepositoryImpl;
import com.example.fileApi.ImageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    CommentRepositoryImpl commentRepository;

    @Override
    public CommentDTO updateComment(Long id, String comment) {
        return CommentAdapter.toCommentDTO(commentRepository.updateComment(comment,id));
    }

    @Override
    @Transactional
    public CommentDTO insertComment(String comment, User user, ImageEntity imageEntity){
        CommentEntity commentEntity = CommentEntity.builder()
                .comment(comment)
                .image(imageEntity)
                .user(user)
                .build();
        commentRepository.save(commentEntity);

        return CommentAdapter.toCommentDTO(commentEntity);
    }


    @Override
    @Transactional
    public List<CommentDTO> getCommentsByImageEntity(Long imageEntityId){

        List<CommentEntity> commentList = commentRepository.findByImageEntity(imageEntityId);
        List<CommentDTO> commentListDto = new ArrayList<>();
        for(CommentEntity comment : commentList)
        {
            commentListDto.add(CommentAdapter.toCommentDTO(comment));
        }
        return commentListDto;
    }
}
