package com.example.test1.controller.dto.profileDTO;

import com.example.test1.converter.BasicProfileConverter;
import com.example.test1.converter.EducationConverter;
import com.example.test1.domain.profile.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public class BasicProfileResponseDTO {

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class BasicProfileGetDTO{

        private String name;
        private LocalDate birthDate;
        private Integer phoneNumber;
        private String email;
        private String photo;

    }


    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class toShowAllProfile{

        BasicProfileResponseDTO.BasicProfileGetDTO basicProfileGetDTO;
        EducationRequestDTO.EducationGetResponseDTO educationGetResponseDTO;
        ExperienceDTO.ExperienceResponseDTO experienceResponseDTO;
        linkDTO.LinkResponseDTO linkResponseDTO;
        RewardDTO.RewardResponseDTO rewardResponseDTO;
        TechDTO.TechResponseDTO techResponseDTO;
        CareerDTO.ResponseDTO careerResponseDTO;

    }
}