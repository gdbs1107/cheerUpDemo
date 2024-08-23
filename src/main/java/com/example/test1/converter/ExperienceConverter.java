package com.example.test1.converter;

import com.example.test1.controller.dto.profileDTO.ExperienceDTO;
import com.example.test1.domain.UserEntity;
import com.example.test1.domain.enumClass.ExperienceCategory;
import com.example.test1.domain.profile.Experience;

public class ExperienceConverter {


    public static Experience toExperience(ExperienceDTO.ExperienceRequestDTO request, UserEntity user) {

        return Experience.builder()
                .experienceCategory(toExperienceCategory(request.getExperienceCategory()))
                .endExp(request.getEndExp())
                .expName(request.getExpName())
                .linkExp(request.getLinkExp())
                .contentExp(request.getContentExp())
                .userEntity(user)
                .startExp(request.getStartExp())
                .topicExp(request.getTopicExp())
                .build();
    }

    public static ExperienceDTO.ExperienceResponseDTO toExperienceResponseDTO(Experience experience) {
        return ExperienceDTO.ExperienceResponseDTO.builder()
                .experienceCategory(experience.getExperienceCategory())
                .linkExp(experience.getLinkExp())
                .topicExp(experience.getTopicExp())
                .endExp(experience.getEndExp())
                .expName(experience.getExpName())
                .contentExp(experience.getContentExp())
                .startExp(experience.getStartExp())
                .build();
    }







    public static ExperienceCategory toExperienceCategory(Integer experienceCategory) {

        if (experienceCategory == null) {
            throw new IllegalArgumentException("Invalid experienceId: " + experienceCategory);
        }

        switch (experienceCategory) {
            case 1:
                return ExperienceCategory.학사;
            case 2:
                return ExperienceCategory.석사;
            case 3:
                return ExperienceCategory.박사;
            default:
                throw new IllegalArgumentException("Invalid experienceId: " + experienceCategory); // 예외를 던지는 방법
        }
    }
}
