package com.example.test1.Repository.profileRepository;

import com.example.test1.domain.UserEntity;
import com.example.test1.domain.postEntity.Community;
import com.example.test1.domain.profile.BasicProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BasicProfileRepository extends JpaRepository<BasicProfile,Long> {

    Optional<BasicProfile> findByIdAndUserEntity(Long basicProfileId, UserEntity userEntity);
    BasicProfile findByUserEntity(UserEntity userEntity);


}
