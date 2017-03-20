package com.example.post.image;

import com.example.loginAPI.Token;

import java.util.List;

/**
 * Created by kokoghlanian on 07/03/2017.
 */
public interface ImageService {

    void updateLike(ImageEntity imageEntity);
    void updateDislike(ImageEntity imageEntity);
    void updateTitle(ImageEntity imageEntity, String title);
    List<ImageDTO> getImages();
    ImageDTO insertImage(String title, Token token);
    ImageDTO findById(Long id);
    ImageDTO findByTitle(String title);
    List<ImageDTO> findByToken(Token token);

}

