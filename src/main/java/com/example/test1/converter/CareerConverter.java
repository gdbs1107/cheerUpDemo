package com.example.test1.converter;

import com.example.test1.controller.dto.profileDTO.CareerDTO;
import com.example.test1.controller.dto.profileDTO.linkDTO;
import com.example.test1.domain.UserEntity;
import com.example.test1.domain.profile.Career;
import com.example.test1.domain.profile.Link;

public class CareerConverter {

    public static Career toConvert(CareerDTO.CareerRequestDTO requestDTO, UserEntity user) {

        return Career.builder()
                .companyName(requestDTO.getCompanyName())
                .userEntity(user)
                .duties(requestDTO.getDuties())
                .endComp(requestDTO.getEndComp())
                .startComp(requestDTO.getStartComp())
                .position(requestDTO.getPosition())
                .companyFunction(requestDTO.getCompanyFunction())
                .build();
    }

    public static CareerDTO.ResponseDTO toResponseDTO(Career career) {

        return CareerDTO.ResponseDTO.builder()
                .companyName(career.getCompanyName())
                .companyFunction(career.getCompanyFunction())
                .duties(career.getDuties())
                .endComp(career.getEndComp())
                .position(career.getPosition())
                .startComp(career.getStartComp())
                .build();
    }
}

