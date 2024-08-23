package com.example.test1.controller.dto.profileDTO;

import com.example.test1.domain.enumClass.TechLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public class CareerDTO {

    @Getter
    public static class CareerRequestDTO{

        private String companyName;
        private LocalDate startComp;
        private LocalDate endComp;
        private String position;
        private String companyFunction;
        private String duties;

    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResponseDTO{

        private String companyName;
        private LocalDate startComp;
        private LocalDate endComp;
        private String position;
        private String companyFunction;
        private String duties;
    }
}
