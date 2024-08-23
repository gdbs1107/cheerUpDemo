package com.example.test1.service.techService;

import com.example.test1.controller.dto.profileDTO.TechDTO;
import org.springframework.stereotype.Service;

@Service
public interface TechService {
    void save(TechDTO.TechRequestDTO request, String username);

    void update(TechDTO.TechRequestDTO request, String username);

    void delete(String username);


    TechDTO.TechResponseDTO getTech(String username);
}
