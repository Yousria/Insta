package com.example.fileApi;

import com.example.fileApi.stockage.StorageFileNotFoundException;
import com.example.fileApi.stockage.StorageService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.slf4j.Logger;


import java.io.IOException;
import java.util.stream.Collectors;

/**
 * Created by Nicolas on 06/03/2017.
 */

    @Controller
    public class FileUploadController {
    String album;
        Logger logger= LoggerFactory.getLogger(FileUploadController.class);
        private final StorageService storageService;

        @Autowired
        public FileUploadController(StorageService storageService) {
            this.storageService = storageService;
        }

        @GetMapping("/album/{album}")
        public String listUploadedFiles(@PathVariable("album")String album, Model model) throws IOException {
            System.out.println(album);
            this.album=album;
            model.addAttribute("files", storageService
                    .loadAll()
                    .filter(path -> path.toString().contains(album))
                    .map(path ->
                            MvcUriComponentsBuilder
                                    .fromMethodName(FileUploadController.class, "serveFile", path.getFileName().toString())
                                    .build().toString())
                    .collect(Collectors.toList()));

            return "uploadForm";
        }

        @GetMapping("/album/files/{filename:.+}")
        @ResponseBody
        public ResponseEntity<Resource> serveFile(@PathVariable("filename") String filename) {

            Resource file = storageService.loadAsResource(filename);
            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+file.getFilename()+"\"")
                    .body(file);
        }





    @PostMapping("/album/")
        public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                       RedirectAttributes redirectAttributes) {
        logger.info("in post : "+album);
            storageService.store(album,file);
            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded " + file.getOriginalFilename() + "!");

        logger.info("fin du post : "+album);
            return "redirect:/album/"+album;
        }

        @ExceptionHandler(StorageFileNotFoundException.class)
        public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc) {
            return ResponseEntity.notFound().build();
        }

    }

