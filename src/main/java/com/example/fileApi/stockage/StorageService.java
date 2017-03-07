package com.example.fileApi.stockage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

    void init();

    void store(String album,MultipartFile file);

    Stream<Path> loadAll();
    Stream<Path> loadAll(String album);

    Path load(String filename);

    Resource loadAsResource(String filename);
    void deleteAll(String album);
    void deleteAll();

}
