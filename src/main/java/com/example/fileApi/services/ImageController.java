package com.example.fileApi.services;

import com.example.fileApi.*;
import com.example.loginAPI.Role;
import com.example.loginAPI.Service.UserServices;
import com.example.loginAPI.User;
import com.example.loginAPI.UserAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.List;

import static com.example.loginAPI.Role.USER;

/**
 * Created by Nicolas on 09/04/2017.
 */
@Controller
public class ImageController {
    @Autowired
    ImageService imageService;

    @Autowired
    AlbumService albumService;

    @Autowired
    UserServices userServices;

    @RequestMapping(value = "/fichier", method = RequestMethod.GET)
    public String showUploadForm(Model model) {

        return "upload";
    }

    @RequestMapping(value = "/doUpload", method = RequestMethod.POST)
    public String handleFileUpload(
                                   @RequestParam("fileUpload") MultipartFile fileUpload, @RequestParam("album_name") String album_name, @RequestParam("pseudo") String pseudo,@RequestParam("title") String title) throws Exception {

        imageService.insertImage(title,AlbumAdapter.toAlbumEntity(albumService.findByTitleAndPseudo(album_name,pseudo)), fileUpload.getBytes());
        System.out.println(imageService.findByTitle(title).getId());
        return "redirect:/fichier";

    }
    @ResponseBody
    @GetMapping(value = "/getImage/{id}")
    public ImageDTO getImage(@PathVariable("id") Long id){
        ImageDTO imageDTO=imageService.findById(id);
        System.out.println(imageDTO.getTitle());
        return imageDTO;
    }
    @ResponseBody
    @GetMapping(value = "/getRandomImage")
    public List<ImageDTO> getImageRandom(){
        return imageService.getRandomImages();
    }
    @ResponseBody
    @RequestMapping(value = "/likeImage/{id}", method = RequestMethod.POST)
    public ImageDTO likeImage(@PathVariable("id") Long id){
        ImageDTO imageDTO=imageService.findById(id);
        imageService.updateDislike(ImageAdapter.toImageEntity(imageDTO));
        imageDTO=imageService.findById(id);
        return imageDTO;
    }
    @ResponseBody
    @RequestMapping(value = "/dislikeImage/{id}", method = RequestMethod.POST)
    public ImageDTO dislikeImage(@PathVariable("id") Long id){
        ImageDTO imageDTO=imageService.findById(id);
        imageService.updateLike(ImageAdapter.toImageEntity(imageDTO));
        imageDTO=imageService.findById(id);
        return imageDTO;
    }
    @ResponseBody
    @RequestMapping(value = "/UpdateTitleImage/{id}", method = RequestMethod.POST)
    public ImageDTO updateTitleImage(@PathVariable("id") Long id,@RequestParam("title") String title){
        ImageDTO imageDTO=imageService.findById(id);
        imageService.updateTitle(ImageAdapter.toImageEntity(imageDTO),title);
        imageDTO=imageService.findById(id);
        return imageDTO;
    }

}
