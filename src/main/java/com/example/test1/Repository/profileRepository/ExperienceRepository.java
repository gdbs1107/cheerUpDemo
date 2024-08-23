package com.example.test1.Repository.profileRepository;

import com.example.test1.domain.UserEntity;
import com.example.test1.domain.profile.Experience;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExperienceRepository extends JpaRepository<Experience,Long> {
    Experience findByUserEntity(UserEntity userEntity);
}
