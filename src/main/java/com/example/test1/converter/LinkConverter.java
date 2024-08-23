package com.example.test1.converter;

import com.example.test1.controller.dto.profileDTO.linkDTO;
import com.example.test1.domain.UserEntity;
import com.example.test1.domain.profile.Link;

public class LinkConverter {

    public static Link toConvert(linkDTO.LinkRequestDTO requestDTO, UserEntity user) {

        return Link.builder()
                .address(requestDTO.getAddress())
                .userEntity(user)
                .build();
    }

    public static linkDTO.LinkResponseDTO toLinkResponseDTO(Link link) {

        return linkDTO.LinkResponseDTO.builder()
                .address(link.getAddress())
                .build();
    }
}
