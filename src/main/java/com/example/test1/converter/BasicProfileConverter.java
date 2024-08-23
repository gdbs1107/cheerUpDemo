package com.example.test1.converter;

import com.example.test1.controller.dto.profileDTO.*;
import com.example.test1.domain.UserEntity;
import com.example.test1.domain.profile.*;

public class BasicProfileConverter {

    public static BasicProfile toBasicProfile(BasicProfileRequestDTO.BasicProfileSaveRequestDTO request, UserEntity user) {

        return BasicProfile.builder()
                .userEntity(user)
                .name(request.getName())
                .phoneNumber(request.getPhoneNumber())
                .email(request.getEmail())
                .birthDate(request.getBirthDate())
                .build();
    }

    public static BasicProfileResponseDTO.BasicProfileGetDTO toBasicProfileGetDTO(BasicProfile basicProfile) {

        return BasicProfileResponseDTO.BasicProfileGetDTO.builder()
                .name(basicProfile.getName())
                .phoneNumber(basicProfile.getPhoneNumber())
                .email(basicProfile.getEmail())
                .birthDate(basicProfile.getBirthDate())
                .photo(basicProfile.getImagePath())
                .build();
    }

    public static BasicProfileResponseDTO.toShowAllProfile toShowAllProfile(BasicProfileResponseDTO.BasicProfileGetDTO basicProfileGetDTO,
    EducationRequestDTO.EducationGetResponseDTO educationGetResponseDTO,
    ExperienceDTO.ExperienceResponseDTO experienceResponseDTO,
    linkDTO.LinkResponseDTO linkResponseDTO,
    RewardDTO.RewardResponseDTO rewardResponseDTO,
    TechDTO.TechResponseDTO techResponseDTO,
    CareerDTO.ResponseDTO careerResponseDTO){

        return BasicProfileResponseDTO.toShowAllProfile.builder()
                .basicProfileGetDTO(basicProfileGetDTO)
                .educationGetResponseDTO(educationGetResponseDTO)
                .experienceResponseDTO(experienceResponseDTO)
                .linkResponseDTO(linkResponseDTO)
                .rewardResponseDTO(rewardResponseDTO)
                .techResponseDTO(techResponseDTO)
                .careerResponseDTO(careerResponseDTO)
                .build();



    }
}
