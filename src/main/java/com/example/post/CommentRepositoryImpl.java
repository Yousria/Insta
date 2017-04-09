package com.example.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by kokoghlanian on 20/03/2017.
 */
@Repository
public interface CommentRepositoryImpl extends JpaRepository<CommentEntity, Long> {

    @Query("update CommentEntity set comment = :new_comment where id = :id")
    void updateComment(@Param("new_comment")String comment, @Param("id") Long id);

    //List<CommentEntity> findByImageEntity(Long id);
    //List<CommentEntity> findByImageEntity(ImageEntity imageEntity, Pageable pageable );

}
