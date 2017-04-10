package com.example.fileApi.services;

import com.example.fileApi.*;
import com.example.loginAPI.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Nicolas on 09/04/2017.
 */
@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    AlbumRepository albumRepository;



    //Ajouter l'user pour l'update et la création
    @Override
    @Transactional
    public void updateTitle(AlbumEntity albumEntity, String title) {
        if(albumEntity == null)
            throw new IllegalArgumentException();

        albumRepository.updateTitle(title,albumEntity.getId());
    }

    @Override
    @Transactional
    public AlbumDTO insertAlbum(String title,  User user){
        AlbumEntity albumEntity = AlbumEntity.builder()
                .title(title)
                .user(user)
                .build();
        albumRepository.save(albumEntity);
        return AlbumAdapter.toAlbumDTO(albumEntity);
    }

    @Override
    @Transactional()
    public AlbumDTO findById(Long id) {
        return AlbumAdapter.toAlbumDTO(albumRepository.findById(id).get());
    }

    @Override
    @Transactional(readOnly = true)
    public AlbumDTO findByTitle(String title) {
        return AlbumAdapter.toAlbumDTO(albumRepository.findByTitle(title).get());
    }




}