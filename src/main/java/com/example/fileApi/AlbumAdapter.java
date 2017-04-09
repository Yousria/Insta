package com.example.fileApi;


/**
 * Created by Nicolas on 09/04/2017.
 */

public class AlbumAdapter {
    public static AlbumDTO toAlbumDTO(AlbumEntity albumEntity){

        return AlbumDTO.builder()
                .id_album(albumEntity.getId_album())
                .title(albumEntity.getTitle())
                .imageEntityList(albumEntity.getImageEntityList())
                .user(albumEntity.getUser()).build();
    }

    public static AlbumEntity toAlbumEntity(AlbumDTO albumDto) {

        return AlbumEntity.builder()
                .id_album(albumDto.getId_album())
                .title(albumDto.getTitle())
                .imageEntityList(albumDto.getImageEntityList())
                .user(albumDto.getUser()).build();
    }
}
