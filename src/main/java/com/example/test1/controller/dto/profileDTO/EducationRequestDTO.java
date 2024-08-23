package com.example.test1.controller.dto.profileDTO;

import com.example.test1.domain.enumClass.Degree;
import com.example.test1.domain.enumClass.Major;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public class EducationRequestDTO {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class EducationSaveRequestDTO{

        @NotNull
        private Integer degree;
        private String schoolName;

        private LocalDate startSchool;
        private LocalDate endSchool;

        private Integer major;
        private String majorName;

    }


    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class EducationGetResponseDTO{


        private Degree degree;
        private String schoolName;

        private LocalDate startSchool;
        private LocalDate endSchool;

        private Major major;
        private String majorName;

    }
}
