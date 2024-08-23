package com.example.test1.Repository;

import com.example.test1.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {


    Boolean existsByUsername(String username);
    Optional<UserEntity> findByUsername(String username);
}
