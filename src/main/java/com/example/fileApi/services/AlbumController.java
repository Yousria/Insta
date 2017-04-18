package com.example.fileApi.services;

import com.example.fileApi.AlbumAdapter;
import com.example.fileApi.AlbumDTO;
import com.example.fileApi.ImageDTO;
import com.example.fileApi.ImageEntity;
import com.example.loginAPI.Service.UserServices;
import com.example.loginAPI.UserAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Nicolas on 09/04/2017.
 */
public class AlbumController {
    @Autowired
    AlbumService albumService;

    @Autowired
    UserServices userServices;

    @RequestMapping(value = "/addAlbum", method = RequestMethod.POST)
    public String addAlbum(
            @RequestParam("albumName") String albumName, @RequestParam("pseudo") String pseudo) throws Exception {

       albumService.insertAlbum(albumName, UserAdapter.toUser(userServices.getUserByPseudo(pseudo)));

        return "redirect:/fichier";

    }
    @RequestMapping(value = "/updateAlbumTitle", method = RequestMethod.POST)
    public String updateAlbumTitle(
            @RequestParam("albumName") String albumName, @RequestParam("pseudo") String pseudo,@RequestParam("newName") String newName) throws Exception {

        albumService.updateTitle(AlbumAdapter.toAlbumEntity(albumService.findByTitleAndPseudo(albumName,pseudo)),newName);

        return "redirect:/fichier";

    }
    @ResponseBody
    @GetMapping(value = "/getAlbums/{pseudo}")
    public List<AlbumDTO> getAllAlbum(
            @PathVariable("pseudo") String pseudo) throws Exception {

        return albumService.findAllByUser(pseudo);
    }



}
