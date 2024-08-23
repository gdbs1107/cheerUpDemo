package com.example.test1.Repository.postRepository;

import com.example.test1.domain.UserEntity;
import com.example.test1.domain.postEntity.PostPortfolio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostPortfolioRepository extends JpaRepository<PostPortfolio, Long> {
    Optional<PostPortfolio> findByIdAndUserEntity(Long portfolioId, UserEntity userEntity);

}
