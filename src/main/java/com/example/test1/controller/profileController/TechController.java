package com.example.test1.controller.profileController;

import com.example.test1.apiPayload.ApiResponse;
import com.example.test1.controller.dto.profileDTO.RewardDTO;
import com.example.test1.controller.dto.profileDTO.TechDTO;
import com.example.test1.service.techService.TechService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tech")
@RequiredArgsConstructor
public class TechController {


    private final TechService techService;

    @PostMapping("/")
    public ApiResponse<String> saveTech(@RequestBody TechDTO.TechRequestDTO request,
                                          @AuthenticationPrincipal UserDetails userDetails) {

        techService.save(request, userDetails.getUsername());
        return ApiResponse.onSuccess("save tech success");
    }

    @PutMapping("/")
    public ApiResponse<String> updateTech(@RequestBody TechDTO.TechRequestDTO request,
                                            @AuthenticationPrincipal UserDetails userDetails){

        techService.update(request,userDetails.getUsername());
        return ApiResponse.onSuccess("update tech success");
    }

    @DeleteMapping("/")
    public ApiResponse<String> deleteTech(@AuthenticationPrincipal UserDetails userDetails) {
        techService.delete(userDetails.getUsername());
        return ApiResponse.onSuccess("delete tech success");
    }

    @GetMapping("/")
    public ApiResponse<TechDTO.TechResponseDTO> getTech(@AuthenticationPrincipal UserDetails userDetails) {
        return ApiResponse.onSuccess(techService.getTech(userDetails.getUsername()));
    }
}
