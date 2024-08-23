package com.example.test1.Repository.profileRepository;

import com.example.test1.domain.UserEntity;
import com.example.test1.domain.profile.Reward;
import com.example.test1.domain.profile.Tech;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechRepository extends JpaRepository<Tech, Long> {
    Tech findByUserEntity(UserEntity userEntity);
}
