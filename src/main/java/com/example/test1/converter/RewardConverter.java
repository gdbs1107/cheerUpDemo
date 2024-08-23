package com.example.test1.converter;

import com.example.test1.controller.dto.profileDTO.RewardDTO;
import com.example.test1.domain.UserEntity;
import com.example.test1.domain.profile.Reward;

public class RewardConverter {

    public static Reward toReward(RewardDTO.RewardRequestDTO requestDTO, UserEntity user){
        return Reward.builder()
                .rewardName(requestDTO.getRewardName())
                .authentication(requestDTO.getAuthentication())
                .userEntity(user)
                .publishDate(requestDTO.getPublishDate())
                .publisher(requestDTO.getPublisher())
                .build();
    }

    public static RewardDTO.RewardResponseDTO toResponse(Reward requestDTO){
        return RewardDTO.RewardResponseDTO.builder()
                .rewardName(requestDTO.getRewardName())
                .authentication(requestDTO.getAuthentication())
                .publishDate(requestDTO.getPublishDate())
                .publisher(requestDTO.getPublisher())
                .build();
    }
}
