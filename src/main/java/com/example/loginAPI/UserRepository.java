package com.example.loginAPI;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by bench on 07/03/2017.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByPseudo(String pseudo);

    Optional<User> findById(Long id);




}
