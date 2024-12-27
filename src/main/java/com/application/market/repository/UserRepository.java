package com.application.market.repository;

import com.application.market.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select * from users where username = :username", nativeQuery = true)
    Optional<User> findByUsername(String username);

    @Query(value = "select * from users where email = :email limit 1", nativeQuery = true)
    Optional<User> findByEmail(String email);

}

