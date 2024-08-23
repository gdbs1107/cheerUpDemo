package com.example.test1.controller.profileController;

import com.example.test1.apiPayload.ApiResponse;
import com.example.test1.controller.dto.profileDTO.ExperienceDTO;
import com.example.test1.service.experienceService.ExperienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/experience")
@RequiredArgsConstructor
public class ExperienceController {

    private final ExperienceService experienceService;

    @PostMapping("/")
    public ApiResponse<String> saveExperience(@RequestBody ExperienceDTO.ExperienceRequestDTO request,
                                              @AuthenticationPrincipal UserDetails userDetails) {

        experienceService.save(request, userDetails.getUsername());
        return ApiResponse.onSuccess("save experience success");
    }

    @PutMapping("/")
    public ApiResponse<String> updateExperience(@RequestBody ExperienceDTO.ExperienceRequestDTO request,
                                                @AuthenticationPrincipal UserDetails userDetails){

        experienceService.update(request,userDetails.getUsername());
        return ApiResponse.onSuccess("update experience success");
    }

    @DeleteMapping("/")
    public ApiResponse<String> deleteExperience(@AuthenticationPrincipal UserDetails userDetails) {
        experienceService.delete(userDetails.getUsername());
        return ApiResponse.onSuccess("delete experience success");
    }

    @GetMapping("/")
    public ApiResponse<ExperienceDTO.ExperienceResponseDTO> getExperience(@AuthenticationPrincipal UserDetails userDetails) {
        return ApiResponse.onSuccess(experienceService.getExperience(userDetails.getUsername()));
    }

}
