package com.example.fileApi.services;

import com.example.fileApi.AlbumAdapter;
import com.example.fileApi.AlbumEntity;
import com.example.fileApi.ImageDTO;
import com.example.loginAPI.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Nicolas on 09/04/2017.
 */
@Controller
public class ImageController {
    @Autowired
    ImageService imageService;


    @RequestMapping(value = "/fichier", method = RequestMethod.GET)
    public String showUploadForm(Model model) {
        return "upload";
    }

    @RequestMapping(value = "/doUpload", method = RequestMethod.POST)
    public String handleFileUpload(
                                   @RequestParam("fileUpload") MultipartFile fileUpload) throws Exception {
System.out.println("in");
                 AlbumEntity album = new AlbumEntity();
                 album.setTitle("abc");
                System.out.println("Saving file: " + fileUpload.getOriginalFilename());
                imageService.insertImage(fileUpload.getOriginalFilename(),album, fileUpload.getBytes());




        return "in";
    }
}
