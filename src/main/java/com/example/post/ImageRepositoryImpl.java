package com.example.post;

import com.example.loginAPI.Token;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD:src/main/java/com/example/post/image/ImageRepository.java
=======
import java.util.Optional;

>>>>>>> 8ff7f1e7f9e839600ecbceea96fac47e80d55a68:src/main/java/com/example/post/ImageRepositoryImpl.java
/**
 * Created by kokoghlanian on 07/03/2017.
 */

@Repository
public interface ImageRepositoryImpl extends JpaRepository<ImageEntity, Long> {

    @Query("update ImageEntity set title = :new_title where id = :id")
    @Modifying
    int updateTitle(@Param("new_title") String title, @Param("id") Long id);

    @Query("update ImageEntity set likescore = :new_like where id = :id")
    @Modifying
    int updateLike(@Param("new_like") Integer likescore, @Param("id") Long id);

    @Query("update ImageEntity set dislikescore = :new_dislike where id = :id")
    @Modifying
    int updateDislike(@Param("new_dislike") Integer dislikescore, @Param("id") Long id);

    Optional<ImageEntity> findByTitle(String title);

    Optional<ImageEntity> findById(Long id);

    Page<ImageEntity> findByToken(Token token,Pageable pageable);

}
