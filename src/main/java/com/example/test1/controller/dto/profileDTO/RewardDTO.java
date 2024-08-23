package com.example.test1.controller.dto.profileDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public class RewardDTO {

    @Getter
    public static class RewardRequestDTO{

        private String rewardName;
        private String publisher;
        private LocalDate publishDate;
        private String authentication;

    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RewardResponseDTO{

        private String rewardName;
        private String publisher;
        private LocalDate publishDate;
        private String authentication;
    }
}
