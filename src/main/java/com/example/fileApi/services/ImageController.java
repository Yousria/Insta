package com.example.fileApi.services;

import com.example.fileApi.AlbumAdapter;
import com.example.fileApi.AlbumDTO;
import com.example.fileApi.AlbumEntity;
import com.example.fileApi.ImageDTO;
import com.example.loginAPI.Role;
import com.example.loginAPI.Service.UserServices;
import com.example.loginAPI.User;
import com.example.loginAPI.UserAdapter;
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

import java.util.Arrays;

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
        if(albumService.findByTitleAndPseudo("bonjour","monpseudo")==null) {
            albumService.insertAlbum("bonjour", UserAdapter.toUser(userServices.getUserByPseudo("monpseudo")));
        }
        System.out.println(albumService.findByTitleAndPseudo("bonjour","monpseudo"));
        return "upload";
    }

    @RequestMapping(value = "/doUpload", method = RequestMethod.POST)
    public String handleFileUpload(
                                   @RequestParam("fileUpload") MultipartFile fileUpload, @RequestParam("album_name") String album_name, @RequestParam("pseudo") String pseudo) throws Exception {
        System.out.println(fileUpload.getSize());
        System.out.println("avant creation user");
               // User user= User.builder().pseudo("abcd").email("mail@mail.fr").password("monmp").role(USER).build();
               // userServices.createUser("monpseudo","mail@mail.fr","monmp", USER);
        System.out.println("in");
        //        albumService.insertAlbum("bonjour", UserAdapter.toUser(userServices.getUserByPseudo("monpseudo")));
       // System.out.println(albumService.findByTitle("bonjour").getTitle());

        imageService.insertImage(fileUpload.getOriginalFilename(),AlbumAdapter.toAlbumEntity(albumService.findByTitleAndPseudo(album_name,pseudo)), fileUpload.getBytes());
        System.out.println(Arrays.equals(imageService.findByTitle(fileUpload.getOriginalFilename()).getDatas(),(fileUpload.getBytes())));
        return "redirect:/fichier";



    }
}
