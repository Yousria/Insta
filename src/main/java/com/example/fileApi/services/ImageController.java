package com.example.fileApi.services;

import com.example.fileApi.*;
import com.example.loginAPI.Service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by Nicolas on 09/04/2017.
 */
@CrossOrigin
@RestController
@RequestMapping("/image")
public class ImageController {

    private final ImageService imageService;


    private final ProductService productService;


     private final UserServices userServices;
    @Autowired
    public ImageController(ImageService imageService, ProductService productService, UserServices userServices) {
        this.imageService = imageService;
        this.productService = productService;
        this.userServices = userServices;
    }



   @RequestMapping(value = "/fichier", method = RequestMethod.GET)
    public void showUploadForm(Model model) {
//       System.out.println(userServices.getUserByPseudo("b").getPseudo());
        ProductEntity productEntity = ProductAdapter.toProductEntity(productService.insertAlbum("a",userServices.getUserByPseudo("b")));
        productService.insertAlbum("a", productEntity.getUser());
        MultipartFile multipartFile;
        try {
           multipartFile = new MockMultipartFile("file 1",new FileInputStream("image.jpg"));

            handleFileUpload(multipartFile,"a","b","c");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @RequestMapping(value = "/doUpload", method = RequestMethod.POST)
    public ImageDTO handleFileUpload(
                                   @RequestParam("fileUpload") MultipartFile fileUpload, @RequestParam("album_name") String album_name, @RequestParam("pseudo") String pseudo,@RequestParam("title") String title) throws Exception {


        return imageService.insertImage(title, ProductAdapter.toProductEntity(productService.findByTitleAndPseudo(album_name,pseudo)), fileUpload.getBytes());


    }
    @GetMapping(value = "/getImage/{id}")
    public ImageDTO getImage(@PathVariable("id") Long id){
        ImageDTO imageDTO=imageService.findById(id);
        System.out.println(imageDTO.getTitle());
        return imageDTO;
    }
    @GetMapping(value = "/getRandomImage")
    public List<ImageDTO> getImageRandom(){
        List<ImageDTO> result=imageService.getRandomImages();
        System.out.println(result.size());
        return result ;
    }
    @RequestMapping(value = "/likeImage/{id}", method = RequestMethod.POST)
    public ImageDTO likeImage(@PathVariable("id") Long id){
        ImageDTO imageDTO=imageService.findById(id);
        imageService.updateLike(ImageAdapter.toImageEntity(imageDTO));
        imageDTO=imageService.findById(id);
        return imageDTO;
    }
    @RequestMapping(value = "/dislikeImage/{id}", method = RequestMethod.POST)
    public ImageDTO dislikeImage(@PathVariable("id") Long id){
        ImageDTO imageDTO=imageService.findById(id);
        imageService.updateDislike(ImageAdapter.toImageEntity(imageDTO));
        imageDTO=imageService.findById(id);
        return imageDTO;
    }
    @RequestMapping(value = "/UpdateTitleImage/{id}", method = RequestMethod.POST)
    public ImageDTO updateTitleImage(@PathVariable("id") Long id,@RequestParam("title") String title){
        ImageDTO imageDTO=imageService.findById(id);
        System.out.println(imageService.updateTitle(ImageAdapter.toImageEntity(imageDTO),title));
        ImageDTO imageDTOres=imageService.findById(id);
        return imageDTOres;
    }

    @GetMapping(value="/ImagesAlbum/{id}")
    public List<ImageDTO> getAllImagesFromAlbum(@PathVariable("id") Long id){
        List<ImageDTO> result =imageService.getByProduct(id);
        return result;
    }

}
