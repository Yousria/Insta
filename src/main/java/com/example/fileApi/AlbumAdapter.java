package com.example.fileApi;


/**
 * Created by Nicolas on 09/04/2017.
 */

public class AlbumAdapter {
    public static AlbumDTO toAlbumDTO(AlbumEntity albumEntity){

        return AlbumDTO.builder()
                .id(albumEntity.getId())
                .title(albumEntity.getTitle())
                .imageEntityList(albumEntity.getImageEntityList())
                .user(albumEntity.getUser()).build();
    }

    public static AlbumEntity toAlbumEntity(AlbumDTO albumDto) {

        return AlbumEntity.builder()
                .id(albumDto.getId())
                .title(albumDto.getTitle())
                .imageEntityList(albumDto.getImageEntityList())
                .user(albumDto.getUser()).build();
    }
}
