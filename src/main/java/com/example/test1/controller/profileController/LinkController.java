package com.example.test1.controller.profileController;

import com.example.test1.apiPayload.ApiResponse;
import com.example.test1.controller.dto.profileDTO.ExperienceDTO;
import com.example.test1.controller.dto.profileDTO.linkDTO;
import com.example.test1.service.linkRepository.LinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/link")
public class LinkController {

    private final LinkService linkService;

    @PostMapping("/")
    public ApiResponse<String> saveLink(@RequestBody linkDTO.LinkRequestDTO request,
                                              @AuthenticationPrincipal UserDetails userDetails) {

        linkService.save(request, userDetails.getUsername());
        return ApiResponse.onSuccess("save link success");
    }

    @PutMapping("/")
    public ApiResponse<String> updateLink(@RequestBody linkDTO.LinkRequestDTO request,
                                                @AuthenticationPrincipal UserDetails userDetails){

        linkService.update(request,userDetails.getUsername());
        return ApiResponse.onSuccess("update link success");
    }

    @DeleteMapping("/")
    public ApiResponse<String> deleteLink(@AuthenticationPrincipal UserDetails userDetails) {
        linkService.delete(userDetails.getUsername());
        return ApiResponse.onSuccess("delete experience success");
    }

    @GetMapping("/")
    public ApiResponse<linkDTO.LinkResponseDTO> getLink(@AuthenticationPrincipal UserDetails userDetails) {
        return ApiResponse.onSuccess(linkService.getLink(userDetails.getUsername()));
    }
}
