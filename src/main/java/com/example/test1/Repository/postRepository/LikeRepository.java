package com.example.test1.Repository.postRepository;

import com.example.test1.domain.UserEntity;
import com.example.test1.domain.postEntity.Community;
import com.example.test1.domain.postEntity.Likes;
import com.example.test1.domain.postEntity.PostPortfolio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Likes,Long> {
    Optional<Likes> findByCommunityAndUserEntity(Community community, UserEntity userEntity);
    void deleteByCommunityAndUserEntity(Community community, UserEntity userEntity);

    Optional<Likes> findByPostPortfolioAndUserEntity(PostPortfolio postPortfolio, UserEntity userEntity);

    Integer countByCommunityId(Long communityId);
}
