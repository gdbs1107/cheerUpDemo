package com.example.test1.service.careerService;

import com.example.test1.controller.dto.profileDTO.CareerDTO;
import org.springframework.stereotype.Service;

@Service
public interface CareerService {
    void save(CareerDTO.CareerRequestDTO request, String username);

    void update(CareerDTO.CareerRequestDTO request, String username);

    void delete(String username);

    CareerDTO.ResponseDTO getCareer(String username);
}
