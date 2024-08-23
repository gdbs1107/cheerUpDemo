package com.example.test1.Repository.profileRepository;

import com.example.test1.domain.UserEntity;
import com.example.test1.domain.profile.Link;
import com.example.test1.domain.profile.Reward;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RewardRepository extends JpaRepository<Reward, Long> {
    Reward findByUserEntity(UserEntity userEntity);
}
