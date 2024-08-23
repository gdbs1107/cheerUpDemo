package com.example.test1.converter;

import com.example.test1.controller.dto.profileDTO.TechDTO;
import com.example.test1.domain.UserEntity;
import com.example.test1.domain.enumClass.Degree;
import com.example.test1.domain.enumClass.TechLevel;
import com.example.test1.domain.profile.Tech;

public class TechConverter {

    public static Tech toTech(TechDTO.TechRequestDTO request, UserEntity user){

        return Tech.builder()
                .techLevel(toTechLevel(request.getTechLevel()))
                .techName(request.getTechName())
                .userEntity(user)
                .build();
    }

    public static TechDTO.TechResponseDTO toResponseDTO(Tech tech){
        return TechDTO.TechResponseDTO.builder()
                .techLevel(tech.getTechLevel())
                .techName(tech.getTechName())
                .build();
    }


    public static TechLevel toTechLevel(Integer techLevel){
        if (techLevel == null) {
            throw new IllegalArgumentException("Invalid techLevel: " + techLevel);
        }

        switch (techLevel) {
            case 1:
                return TechLevel.상;
            case 2:
                return TechLevel.중;
            case 3:
                return TechLevel.하;
            default:
                throw new IllegalArgumentException("Invalid techLevel: " + techLevel); // 예외를 던지는 방법
        }
    }
}
