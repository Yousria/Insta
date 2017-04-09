package com.example.fileApi.services;

import com.example.fileApi.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Nicolas on 09/04/2017.
 */

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    ImageRepository imageRepository;

    @Override
    @Transactional
    public void updateLike(ImageEntity imageEntity) {
        if(imageEntity == null)
            throw new IllegalArgumentException();

        imageRepository.updateLike(imageEntity.getLikescore()+1,imageEntity.getId());
    }

    @Override
    @Transactional
    public void updateDislike(ImageEntity imageEntity) {
        if(imageEntity == null)
            throw new IllegalArgumentException();

        imageRepository.updateDislike(imageEntity.getDislikescore()+1,imageEntity.getId());
    }

    //Ajouter l'user pour l'update et la création
    @Override
    @Transactional
    public void updateTitle(ImageEntity imageEntity, String title,String token) {
        if(imageEntity == null)
            throw new IllegalArgumentException();

        imageRepository.updateTitle(title,imageEntity.getId());
    }

    @Override
    @Transactional
    public ImageDTO insertImage(String title, AlbumEntity album,byte[] datas){
        ImageEntity imageEntity = ImageEntity.builder()
                .likescore(0)
                .dislikescore(0)
                .title(title)
                .datas(datas)
                .album(album)
                .build();
        imageRepository.save(imageEntity);
        return ImageAdapter.toImageDTO(imageEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public ImageDTO findById(Long id) {
        return ImageAdapter.toImageDTO(imageRepository.findById(id).get());
    }

    @Override
    @Transactional(readOnly = true)
    public ImageDTO findByTitle(String title) {
        return ImageAdapter.toImageDTO(imageRepository.findByTitle(title).get());
    }




    @Override
    public List<ImageDTO> getImages(AlbumEntity album) {

        return ImageAdapter.toListImageDTO(album.getImageEntityList());
    }

}
