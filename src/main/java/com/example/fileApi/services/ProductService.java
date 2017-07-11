package com.example.fileApi.services;

import com.example.fileApi.ProductDTO;
import com.example.fileApi.ProductEntity;
import com.example.loginAPI.User;

import java.util.List;

/**
 * Created by Nicolas on 09/04/2017.
 */
public interface ProductService {
    ProductDTO insertAlbum(String title, User user);
    int updateTitle(ProductEntity album, String title);
    ProductDTO findById(Long id);
    ProductDTO findByTitle(String title);
    ProductDTO findByTitleAndPseudo(String title, String pseudo);
    List<ProductDTO> findAllByUser(Long id);
    void deleteProduct(ProductEntity productEntity);

}
