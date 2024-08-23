package com.example.test1.controller.dto.profileDTO;

import com.example.test1.domain.enumClass.TechLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class TechDTO {

    @Getter
    public static class TechRequestDTO{
        private String techName;
        private Integer techLevel;

    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TechResponseDTO{
        private String techName;
        private TechLevel techLevel;
    }

}
