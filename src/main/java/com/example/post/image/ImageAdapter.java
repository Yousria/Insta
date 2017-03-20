package com.example.post.image;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kokoghlanian on 06/03/2017.
 */
public class ImageAdapter {

    public static ImageDTO toImageDTO(ImageEntity imageEntity){

        return ImageDTO.builder()
                .id(imageEntity.getId())
                .token(imageEntity.getToken())
                .dislikescore(imageEntity.getDislikescore())
                .title(imageEntity.getTitle())
                .likescore(imageEntity.getLikescore()).build();
    }

    public static ImageEntity toImageEntity(ImageDTO imageDto) {

        return ImageEntity.builder()
                .id(imageDto.getId())
                .token(imageDto.getToken())
                .dislikescore(imageDto.getDislikescore())
                .title(imageDto.getTitle())
                .likescore(imageDto.getLikescore()).build();
    }

    public static List<ImageDTO> toListImageDTO(List<ImageEntity> imageEntityList){
        List<ImageDTO> imageEntityDTOList
                = new ArrayList<>();

        for(int i = 0; i < imageEntityDTOList.size(); i++)
            imageEntityDTOList.add(toImageDTO(imageEntityList.get(i)));

        return imageEntityDTOList;
    }
}
