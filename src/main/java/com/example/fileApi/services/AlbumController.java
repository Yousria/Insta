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

@CrossOrigin
@RestController
@RequestMapping("/albums")
public class AlbumController {
    @Autowired
    AlbumService albumService;

    @Autowired
    UserServices userServices;

    @RequestMapping(value = "/addAlbum", method = RequestMethod.POST)
    public AlbumDTO  addAlbum(
            @RequestParam("albumName") String albumName, @RequestParam("pseudo") String pseudo) throws Exception {

       return albumService.insertAlbum(albumName, userServices.getUserByPseudo(pseudo));

      //  return "redirect:/fichier";

    }
    @RequestMapping(value = "/updateAlbumTitle", method = RequestMethod.POST)
    public int updateAlbumTitle(
            @RequestParam("albumName") String albumName, @RequestParam("pseudo") String pseudo,@RequestParam("newName") String newName) throws Exception {
        AlbumDTO album=albumService.findByTitleAndPseudo(albumName,pseudo);
        if(album!=null)
       return  albumService.updateTitle(AlbumAdapter.toAlbumEntity(album),newName);
        return 0;



    }
    @ResponseBody
    @GetMapping(value = "/getAlbums/{id}")
    public List<AlbumDTO> getAllAlbum(
            @PathVariable("id") Long id) throws Exception {
        List<AlbumDTO> result=albumService.findAllByUser(id);
        return result ;
    }




}
