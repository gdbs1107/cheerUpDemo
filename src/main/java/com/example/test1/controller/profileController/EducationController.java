package com.example.test1.controller.profileController;

import com.example.test1.apiPayload.ApiResponse;
import com.example.test1.controller.dto.profileDTO.EducationRequestDTO;
import com.example.test1.service.educationService.EducationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/education")
@RequiredArgsConstructor
public class EducationController {

    private final EducationService educationService;

    @PostMapping("/")
    public ApiResponse<String> saveEducation(@RequestBody EducationRequestDTO.EducationSaveRequestDTO request,
                                             @AuthenticationPrincipal UserDetails userDetails) {

        educationService.save(request,userDetails.getUsername());
        return ApiResponse.onSuccess("save education success");
    }



    @PutMapping("/")
    public ApiResponse<String> updateEducation(@RequestBody EducationRequestDTO.EducationSaveRequestDTO request,
                                               @AuthenticationPrincipal UserDetails userDetails) {

        educationService.update(request,userDetails.getUsername());
        return ApiResponse.onSuccess("update education success");
    }



    @DeleteMapping("/")
    public ApiResponse<String> deleteEducation(@AuthenticationPrincipal UserDetails userDetails) {
        educationService.delete(userDetails.getUsername());
        return ApiResponse.onSuccess("delete education success");
    }


    @GetMapping("/")
    public ApiResponse<EducationRequestDTO.EducationGetResponseDTO> getEducation(@AuthenticationPrincipal UserDetails userDetails) {
        return ApiResponse.onSuccess(educationService.getEducation(userDetails.getUsername()));
    }
}
