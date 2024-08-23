package com.example.test1.Repository.postRepository;

import com.example.test1.domain.UserEntity;
import com.example.test1.domain.postEntity.Community;
import com.example.test1.domain.postEntity.PinnedCommunity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PinnedCommunityRepository extends JpaRepository<PinnedCommunity,Long> {

    Optional<PinnedCommunity> findByUserEntityAndCommunity(UserEntity userEntity, Community community);

    List<PinnedCommunity> findAllByUserEntity(UserEntity userEntity);
}
