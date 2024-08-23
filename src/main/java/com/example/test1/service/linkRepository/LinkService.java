package com.example.test1.service.linkRepository;

import com.example.test1.controller.dto.profileDTO.linkDTO;
import org.springframework.stereotype.Service;

@Service
public interface LinkService {
    void save(linkDTO.LinkRequestDTO request, String username);

    void update(linkDTO.LinkRequestDTO request, String username);

    void delete(String username);

    linkDTO.LinkResponseDTO getLink(String username);
}
