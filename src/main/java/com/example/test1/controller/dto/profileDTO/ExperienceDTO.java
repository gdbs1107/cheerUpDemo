package com.example.test1.controller.dto.profileDTO;

import com.example.test1.domain.enumClass.ExperienceCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public class ExperienceDTO {

    @Getter
    public static class ExperienceRequestDTO{

        private Integer experienceCategory;

        private String expName;
        private LocalDate startExp;
        private LocalDate endExp;

        private String topicExp;
        private String contentExp;
        private String linkExp;

    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ExperienceResponseDTO{

        private ExperienceCategory experienceCategory;

        private String expName;
        private LocalDate startExp;
        private LocalDate endExp;

        private String topicExp;
        private String contentExp;
        private String linkExp;
    }
}
