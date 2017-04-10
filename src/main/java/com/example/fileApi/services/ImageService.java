package com.example.fileApi.services;

import com.example.fileApi.AlbumEntity;
import com.example.fileApi.ImageDTO;
import com.example.fileApi.ImageEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Nicolas on 09/04/2017.
 */
public interface ImageService {
    public void updateLike(ImageEntity imageEntity);
    ImageDTO insertImage(String title, AlbumEntity album, byte[] datas);
    void updateDislike(ImageEntity imageEntity);
    void updateTitle(ImageEntity imageEntity, String title, String token);
  //  List<ImageDTO> getImages(AlbumEntity albums);
    ImageDTO findById(Long id);
    ImageDTO findByTitle(String title);
}
