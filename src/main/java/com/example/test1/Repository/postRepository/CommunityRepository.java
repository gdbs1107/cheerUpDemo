package com.example.test1.Repository.postRepository;

import com.example.test1.domain.UserEntity;
import com.example.test1.domain.postEntity.Community;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommunityRepository extends JpaRepository<Community,Long> {

    @Query("select c from Community c left join c.userEntity u")
    Optional<Community> findByIdAndUserEntity(Long communityId, UserEntity userEntity);

    // 조회수 순으로 정렬하여 페이지로 반환하는 메서드
    Page<Community> findAllByOrderByViewCountDesc(Pageable pageable);
    List<Community> searchCommunitiesByTitle(String title);

    @Query("SELECT c FROM Community c WHERE c.userEntity.username = :username")
    List<Community> searchCommunitiesByUsername(String username);


    @EntityGraph(attributePaths = {"userEntity", "likes", "comments"})
    @Query("select c from Community c where c.id = :communityId")
    Optional<Community> findById(@Param("communityId") Long communityId);
}
