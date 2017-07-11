package com.example.fileApi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 *  @author Nicolas Sirac
 */
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    @Query("update ProductEntity set title = :new_title where id = :id")
    @Modifying(clearAutomatically = true)
    int updateTitle(@Param("new_title") String title, @Param("id") Long id);

    @Query(nativeQuery = true,value="select pe.* from product pe where pe.id = :id")
    Optional<ProductEntity> findById(@Param("id")Long id);

    @Query(nativeQuery = true,value="Select pe.* from product pe where pe.title = :title")
    Optional<ProductEntity> findByTitle(@Param("title") String title);

    @Query(nativeQuery = true,value="Select pe.* from product pe join users WHERE pe.title = :title and users.pseudo = :pseudo LIMIT 1;")
    Optional<ProductEntity> findByTitleAndPseudo(@Param("title") String title, @Param("pseudo") String pseudo);

    @Query(nativeQuery = true,value="Select pe.* from product pe where pe.users=:id")
    List<ProductEntity> findAllByUser(@Param("id") Long id);
   /* @Query("Select * from album where  user.id= :user_id")
    List<AlbumEntity> findByUser(@Param("user") Long user_id);*/
}
