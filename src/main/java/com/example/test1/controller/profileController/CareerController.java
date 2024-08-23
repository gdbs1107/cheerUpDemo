package com.example.test1.controller.profileController;

import com.example.test1.apiPayload.ApiResponse;
import com.example.test1.controller.dto.profileDTO.CareerDTO;
import com.example.test1.controller.dto.profileDTO.linkDTO;
import com.example.test1.service.careerService.CareerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/career")
public class CareerController {

    private final CareerService careerService;

    @PostMapping("/")
    public ApiResponse<String> saveCareer(@RequestBody CareerDTO.CareerRequestDTO request,
                                        @AuthenticationPrincipal UserDetails userDetails) {

        careerService.save(request, userDetails.getUsername());
        return ApiResponse.onSuccess("save career success");
    }

    @PutMapping("/")
    public ApiResponse<String> updateCareer(@RequestBody CareerDTO.CareerRequestDTO request,
                                          @AuthenticationPrincipal UserDetails userDetails){

        careerService.update(request,userDetails.getUsername());
        return ApiResponse.onSuccess("update career success");
    }

    @DeleteMapping("/")
    public ApiResponse<String> deleteCareer(@AuthenticationPrincipal UserDetails userDetails) {
        careerService.delete(userDetails.getUsername());
        return ApiResponse.onSuccess("delete career success");
    }

    @GetMapping("/")
    public ApiResponse<CareerDTO.ResponseDTO> getCareer(@AuthenticationPrincipal UserDetails userDetails) {
        return ApiResponse.onSuccess(careerService.getCareer(userDetails.getUsername()));
    }
}
