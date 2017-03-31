package com.example.post.services;

import com.example.loginAPI.Token;
import com.example.post.ImageAdapter;
import com.example.post.ImageDTO;
import com.example.post.ImageEntity;
import com.example.post.ImageRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by kokoghlanian on 07/03/2017.
 */

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    ImageRepositoryImpl imageRepository;

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

        if(imageEntity.getToken().getToken() != token)
            return;

        imageRepository.updateTitle(title,imageEntity.getId());
    }

    @Override
    @Transactional
    public ImageDTO insertImage(String title, Token token){
        ImageEntity imageEntity = ImageEntity.builder()
                .token(token)
                .likescore(0)
                .dislikescore(0)
                .title(title)
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
    @Transactional(readOnly = true)
    public List<ImageDTO> findByToken(Token token) {
        PageRequest pageRequest = new PageRequest(0,1, Sort.Direction.DESC,"id");
        Page<ImageEntity> imageList = imageRepository.findByToken(token,pageRequest);
        return ImageAdapter.toListImageDTO(imageList.getContent());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ImageDTO> getImages() {

        List<ImageEntity> imageList = imageRepository.findAll();
        return ImageAdapter.toListImageDTO(imageList);
    }

}
