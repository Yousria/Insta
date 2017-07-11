package com.example.fileApi;


import java.util.ArrayList;
import java.util.List;

/**
 * @author Nicolas Sirac
 */

public class ProductAdapter {
    public static ProductDTO toAlbumDTO(ProductEntity productEntity){

        return ProductDTO.builder()
                .id(productEntity.getId())
                .title(productEntity.getTitle())
                .imageEntityList(productEntity.getImageEntityList())
                .user(productEntity.getUser()).build();
    }

    public static ProductEntity toProductEntity(ProductDTO albumDto) {

        return ProductEntity.builder()
                .id(albumDto.getId())
                .title(albumDto.getTitle())
                .imageEntityList(albumDto.getImageEntityList())
                .user(albumDto.getUser()).build();
    }

    public static List<ProductDTO> listToProductDTO(List<ProductEntity> albumEntities) {
        List<ProductDTO> resultList = new ArrayList<>();
        for (ProductEntity productEntity : albumEntities)
            resultList.add(toAlbumDTO(productEntity));
        return resultList;
    }
    public static List<ProductEntity> listToAlbumEntity(List<ProductDTO> albumDTOS) {
        List<ProductEntity> resultList = new ArrayList<>();
        for (ProductDTO albumDTO : albumDTOS)
            resultList.add(toProductEntity(albumDTO));
        return resultList;
    }
}
