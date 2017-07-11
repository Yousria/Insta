package com.example.fileApi.services;

import com.example.fileApi.*;
import com.example.loginAPI.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * Created by Nicolas on 09/04/2017.
 */
@Service
public class ProductServiceImpl implements ProductService {


    ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository albumRepository) {
        this.productRepository = albumRepository;
    }

    //Ajouter l'user pour l'update et la création
    @Override
    @Transactional
    public int updateTitle(ProductEntity productEntity, String title) {
        if(productEntity == null)
            throw new IllegalArgumentException();

        return productRepository.updateTitle(title, productEntity.getId());
    }

    @Override
    @Transactional
    public ProductDTO insertAlbum(String title, User user){
        ProductEntity productEntity = ProductEntity.builder()
                .title(title)
                .user(user)
                .build();
        productRepository.save(productEntity);
        return ProductAdapter.toAlbumDTO(productEntity);
    }

    @Override
    @Transactional()
    public ProductDTO findById(Long id) {
        return ProductAdapter.toAlbumDTO(productRepository.findById(id).get());
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDTO findByTitle(String title) {
        return ProductAdapter.toAlbumDTO(productRepository.findByTitle(title).get());
    }



    @Override
    @Transactional(readOnly = true)
    public ProductDTO findByTitleAndPseudo(String title, String pseudo) {
        Optional<ProductEntity> albumEntityOptional =productRepository.findByTitleAndPseudo(title,pseudo);
        if(albumEntityOptional.isPresent())
            return ProductAdapter.toAlbumDTO(albumEntityOptional.get());
        return null;
    }

    @Override
    public List<ProductDTO> findAllByUser(Long id) {
     //   System.out.println(pseudo);
        return  productRepository.findAllByUser(id).stream()
                .map(ProductAdapter::toAlbumDTO)
                .collect(toList());
    }

    @Override
    public void deleteProduct(ProductEntity productEntity) {
        productRepository.delete(productEntity);
    }
}