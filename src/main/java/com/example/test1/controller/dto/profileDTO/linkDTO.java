package com.example.test1.controller.dto.profileDTO;

import com.example.test1.domain.enumClass.ExperienceCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public class linkDTO {

    @Getter
    public static class LinkRequestDTO{

        private String address;

    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LinkResponseDTO{

        private String address;
    }
}
