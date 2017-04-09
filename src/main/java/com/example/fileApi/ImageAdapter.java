package com.example.fileApi;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nicolas on 09/04/2017.
 */
public class ImageAdapter {
    public static ImageDTO toImageDTO(ImageEntity imageEntity){

        return ImageDTO.builder()
                .id_album(imageEntity.getId_album())
                .dislikescore(imageEntity.getDislikescore())
                .title(imageEntity.getTitle())
                .id_image(imageEntity.getId_image())
                .commentEntityList(imageEntity.getCommentEntityList())
                .likescore(imageEntity.getLikescore()).build();
    }

    public static ImageEntity toImageEntity(ImageDTO imageDto) {

        return ImageEntity.builder()
                .id_image(imageDto.getId_image())
                .id_album(imageDto.getId_album())
                .dislikescore(imageDto.getDislikescore())
                .title(imageDto.getTitle())
                .likescore(imageDto.getLikescore())
                .commentEntityList(imageDto.getCommentEntityList()).build();
    }

    public static List<ImageDTO> toListImageDTO(List<ImageEntity> imageEntityList){
        List<ImageDTO> imageEntityDTOList
                = new ArrayList<>();

        for(ImageEntity imageEntity : imageEntityList)
            imageEntityDTOList.add(toImageDTO(imageEntity));

        return imageEntityDTOList;
    }
}
