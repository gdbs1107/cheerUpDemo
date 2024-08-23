package com.example.test1.Repository.postRepository;

import com.example.test1.domain.UserEntity;
import com.example.test1.domain.postEntity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    @Query("SELECT COUNT(c) FROM Comment c WHERE c.community.id = :communityId")
    Long countByCommunityId(@Param("communityId") Long communityId);
    Optional<Comment> findByIdAndUserEntity(Long commentId, UserEntity userEntity);

    @Query("SELECT COUNT(c) FROM Comment c WHERE c.postPortfolio.id = :postPortfolioId")
    Long countByPostPortfolioId(@Param("postPortfolioId") Long postPortfolioId);

}
