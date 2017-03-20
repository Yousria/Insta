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
    ImageDTO insertAccount(String title, Token token);



    // a corriger
    /*
        méthode d'update
     */
    //ajouter méthode
    /*
    find Image by author
    find Image by title
    find Image by id
     */
}

