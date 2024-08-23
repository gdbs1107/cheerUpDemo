package com.example.test1.Repository.profileRepository;

import com.example.test1.domain.UserEntity;
import com.example.test1.domain.profile.Career;
import com.example.test1.domain.profile.Link;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CareerRepository extends JpaRepository<Career,Long> {

    Career findByUserEntity(UserEntity userEntity);
}
