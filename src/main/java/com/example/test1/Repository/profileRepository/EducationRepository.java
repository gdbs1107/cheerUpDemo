package com.example.test1.Repository.profileRepository;

import com.example.test1.domain.UserEntity;
import com.example.test1.domain.profile.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EducationRepository extends JpaRepository<Education,Long> {

    Education findByUserEntity(UserEntity user);
}
