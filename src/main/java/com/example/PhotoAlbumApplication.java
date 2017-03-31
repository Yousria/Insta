package com.example;

import com.example.fileApi.stockage.StorageProperties;
		import com.example.fileApi.stockage.StorageService;
		import org.springframework.boot.CommandLineRunner;
		import org.springframework.boot.SpringApplication;
		import org.springframework.boot.autoconfigure.SpringBootApplication;
		import org.springframework.boot.context.properties.EnableConfigurationProperties;
		import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class PhotoAlbumApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhotoAlbumApplication.class, args);
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.deleteAll();
			storageService.init();
		};
	}
}
