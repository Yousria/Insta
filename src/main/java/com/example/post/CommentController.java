package com.example.post;

import com.example.fileApi.ImageAdapter;
import com.example.fileApi.ImageEntity;
import com.example.fileApi.services.ImageService;
import com.example.loginAPI.Service.UserServices;
import com.example.loginAPI.User;
import com.example.loginAPI.UserAdapter;
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

    private final UserServices userServices;
    public final CommentService commentService;
    public final ImageService imageService;

    @Autowired
    public CommentController(UserServices userServices, CommentService commentService, ImageService imageService) {
        this.userServices = userServices;
        this.commentService = commentService;
        this.imageService = imageService;
    }

    @PostMapping("/{idComment}/{comment}")
    public CommentDTO updateComment(@PathVariable Long idComment,@PathVariable String comment) {
        return commentService.updateComment(idComment,comment);
    }

    @GetMapping("/{idUser}")
    public List<CommentDTO> getCommentsByImageEntity(@PathVariable Long idUser){
        return commentService.getCommentsByImageEntity(idUser);
    }

    @PostMapping("/{pseudo}/{imageid}/{comment}")
    public CommentDTO insertComment(@PathVariable String pseudo,String comment, Long imageid){
        User user = UserAdapter.toUser(userServices.getUserByPseudo(pseudo));
        ImageEntity imageEntity = ImageAdapter.toImageEntity(imageService.findById(imageid));
        return commentService.insertComment(comment,user,imageEntity);
    }

}
