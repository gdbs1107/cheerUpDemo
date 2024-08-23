package com.example.test1.service.basicProfileService;

import com.example.test1.controller.dto.profileDTO.BasicProfileRequestDTO;
import com.example.test1.controller.dto.profileDTO.BasicProfileResponseDTO;
import com.example.test1.domain.profile.BasicProfile;
import org.springframework.stereotype.Service;

@Service
public interface BasicProfileService {

    BasicProfile save(BasicProfileRequestDTO.BasicProfileSaveRequestDTO request, String username);

    String saveProfileImage(String imagePath, String imageName, String username);

    BasicProfile update(BasicProfileRequestDTO.BasicProfileSaveRequestDTO request, String username, String url);

    void delete(String username);

    BasicProfileResponseDTO.BasicProfileGetDTO getBasicProfile(String username);

    BasicProfileResponseDTO.toShowAllProfile showAllProfile(String username);
}