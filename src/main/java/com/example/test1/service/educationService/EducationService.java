package com.example.test1.service.educationService;

import com.example.test1.controller.dto.profileDTO.EducationRequestDTO;
import org.springframework.stereotype.Service;

@Service
public interface EducationService {
    void save(EducationRequestDTO.EducationSaveRequestDTO request, String username);

    void update(EducationRequestDTO.EducationSaveRequestDTO request, String username);

    void delete(String username);

    EducationRequestDTO.EducationGetResponseDTO getEducation(String username);
}
