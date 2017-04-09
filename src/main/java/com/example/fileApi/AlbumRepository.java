package com.example.fileApi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * Created by Nicolas on 09/04/2017.
 */
public interface AlbumRepository extends JpaRepository<AlbumEntity, Long> {
    @Query("update AlbumEntity set title = :new_title where id = :id")
    @Modifying
    int updateTitle(@Param("new_title") String title, @Param("id") Long id);

    Optional<AlbumEntity> findById(Long id);
    Optional<AlbumEntity> findByTitle(String title);
}
