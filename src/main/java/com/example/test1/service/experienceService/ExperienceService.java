package com.example.test1.service.experienceService;

import com.example.test1.controller.dto.profileDTO.ExperienceDTO;
import org.springframework.stereotype.Service;

@Service
public interface ExperienceService {
    void save(ExperienceDTO.ExperienceRequestDTO request, String username);

    void update(ExperienceDTO.ExperienceRequestDTO request, String username);

    void delete(String username);

    ExperienceDTO.ExperienceResponseDTO getExperience(String username);
}
