package com.example.fileApi.services;

import com.example.fileApi.ProductAdapter;
import com.example.fileApi.ProductDTO;
import com.example.loginAPI.Service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Nicolas Sirac
 */

@CrossOrigin
@RestController
@RequestMapping("/albums")
public class ProductController {
    private final
    ProductService productService;

    private final
    UserServices userServices;

    @Autowired
    public ProductController(ProductService productService, UserServices userServices) {
        this.productService = productService;
        this.userServices = userServices;
    }

    @RequestMapping(value = "/addAlbum", method = RequestMethod.POST)
    public ProductDTO addAlbum(@RequestParam("albumName") String albumName, @RequestParam("pseudo") String pseudo) throws Exception {

       return productService.insertAlbum(albumName, userServices.getUserByPseudo(pseudo));

      //  return "redirect:/fichier";

    }
    @RequestMapping(value = "/updateAlbumTitle", method = RequestMethod.POST)
    public int updateAlbumTitle(@RequestParam("albumName") String albumName, @RequestParam("pseudo") String pseudo,@RequestParam("newName") String newName) throws Exception {
        ProductDTO album=productService.findByTitleAndPseudo(albumName, pseudo);

        if(album!=null) {
            return productService.updateTitle(ProductAdapter.toProductEntity(album), newName);
        }
        return 0;
    }

    @ResponseBody
    @GetMapping(value = "/getAlbums/{id}")
    public List<ProductDTO> getAllAlbum(
            @PathVariable("id") Long id) throws Exception {
        return productService.findAllByUser(id);
    }




}
