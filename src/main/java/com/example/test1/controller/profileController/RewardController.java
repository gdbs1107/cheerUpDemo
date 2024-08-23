package com.example.test1.controller.profileController;

import com.example.test1.apiPayload.ApiResponse;
import com.example.test1.controller.dto.profileDTO.RewardDTO;
import com.example.test1.controller.dto.profileDTO.linkDTO;
import com.example.test1.service.rewardService.RewardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/reward")
public class RewardController {

    private final RewardService rewardService;

    @PostMapping("/")
    public ApiResponse<String> saveReward(@RequestBody RewardDTO.RewardRequestDTO request,
                                        @AuthenticationPrincipal UserDetails userDetails) {

        rewardService.save(request, userDetails.getUsername());
        return ApiResponse.onSuccess("save reward success");
    }

    @PutMapping("/")
    public ApiResponse<String> updateReward(@RequestBody RewardDTO.RewardRequestDTO request,
                                          @AuthenticationPrincipal UserDetails userDetails){

        rewardService.update(request,userDetails.getUsername());
        return ApiResponse.onSuccess("update reward success");
    }

    @DeleteMapping("/")
    public ApiResponse<String> deleteReward(@AuthenticationPrincipal UserDetails userDetails) {
        rewardService.delete(userDetails.getUsername());
        return ApiResponse.onSuccess("delete reward success");
    }

    @GetMapping("/")
    public ApiResponse<RewardDTO.RewardResponseDTO> getReward(@AuthenticationPrincipal UserDetails userDetails) {
        return ApiResponse.onSuccess(rewardService.getReward(userDetails.getUsername()));
    }
}
