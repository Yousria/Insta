package com.example.post;

import com.example.post.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by kokoghlanian on 09/04/2017.
 */
@RestController
@RequestMapping("/comments")
public class CommentController {

    public final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{idComment}/{comment}")
    public CommentDTO updateComment(@PathVariable Long idComment,@PathVariable String comment) {
        return commentService.updateComment(idComment,comment);
    }

    @GetMapping("/{idUser}")
    public List<CommentDTO> getCommentsByImageEntity(@PathVariable Long idUser){
        return commentService.getCommentsByImageEntity(idUser);
    }

}
