package com.example.post.comment;

import com.example.post.image.ImageEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by kokoghlanian on 20/03/2017.
 */
@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    @Query("update CommentEntity set comment = :new_comment where id = :id")
    void updateComment(@Param("new_comment")String comment, @Param("id") Long id);

    Page<CommentEntity> findByImageEntity(ImageEntity imageEntity);
}
