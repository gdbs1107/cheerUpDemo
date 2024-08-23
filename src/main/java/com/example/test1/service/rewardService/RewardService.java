package com.example.test1.service.rewardService;

import com.example.test1.controller.dto.profileDTO.RewardDTO;
import org.springframework.stereotype.Service;

@Service
public interface RewardService {
    void save(RewardDTO.RewardRequestDTO request, String username);

    void update(RewardDTO.RewardRequestDTO request, String username);

    void delete(String username);

    RewardDTO.RewardResponseDTO getReward(String username);
}
