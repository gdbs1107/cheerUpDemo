package com.example.test1.controller.postContoller;

import com.example.test1.apiPayload.ApiResponse;
import com.example.test1.controller.dto.postDTO.CommunityRequestDTO;
import com.example.test1.controller.dto.postDTO.CommunityResponseDTO;
import com.example.test1.service.communityService.CommunityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/community")
public class CommunityController {

    private final CommunityService communityService;

    @PostMapping("/")
    public ApiResponse<String> postCommunity(@RequestBody CommunityRequestDTO.CommunityPostRequestDTO request,
                                             @AuthenticationPrincipal UserDetails userDetails) {

        communityService.postCommunity(request,userDetails.getUsername());
        return ApiResponse.onSuccess("save");
    }

    @PutMapping("/{communityId}")
    public ApiResponse<String> updateCommunity(@PathVariable Long communityId,
                                               @RequestBody CommunityRequestDTO.CommunityPostRequestDTO request,
                                               @AuthenticationPrincipal UserDetails userDetails ){

        communityService.updateCommunity(communityId,request,userDetails.getUsername());

        return ApiResponse.onSuccess("updated");
    }

    @DeleteMapping("/{communityId}")
    public ApiResponse<String> deleterCommunity(@PathVariable Long communityId,
                                                @AuthenticationPrincipal UserDetails userDetails){

        communityService.deleteCommunity(communityId,userDetails.getUsername());
        return ApiResponse.onSuccess("deleted");
    }



    //조회 로직들
    @GetMapping("/{communityId}")
    public ApiResponse<CommunityResponseDTO.communitySearchResponseDTO> getCommunity(@PathVariable Long communityId){

        CommunityResponseDTO.communitySearchResponseDTO findCommunity = communityService.searchCommunity(communityId);

        return ApiResponse.onSuccess(findCommunity);

    }

    @GetMapping("/")
    public ApiResponse<CommunityResponseDTO.communityPageResponseDTO> getAllCommunities(
            @Valid
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<CommunityResponseDTO.communityTitleContentDTO> communitiesPage = communityService.searchAllCommunities(PageRequest.of(page, size));

        CommunityResponseDTO.communityPageResponseDTO response = new CommunityResponseDTO.communityPageResponseDTO(
                communitiesPage.getTotalPages(),
                communitiesPage.getTotalElements(),
                communitiesPage.getContent()
        );

        return ApiResponse.onSuccess(response);
    }

    @PostMapping("/{communityId}/like")
    public ApiResponse<String> toggleLike(@PathVariable Long communityId,
                                          @AuthenticationPrincipal UserDetails userDetails) {
        communityService.toggleLike(communityId, userDetails.getUsername());
        return ApiResponse.onSuccess("Like toggled");
    }


    @GetMapping("/popular")
    public ApiResponse<CommunityResponseDTO.communityPageResponseDTO> getAllCommunitiesByViewCount(
            @Valid
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<CommunityResponseDTO.communityTitleContentDTO> communitiesPage = communityService.searchAllCommunitiesByViewCount(PageRequest.of(page, size));

        CommunityResponseDTO.communityPageResponseDTO response = new CommunityResponseDTO.communityPageResponseDTO(
                communitiesPage.getTotalPages(),
                communitiesPage.getTotalElements(),
                communitiesPage.getContent()
        );

        return ApiResponse.onSuccess(response);
    }

    // 게시글에 핀 버튼을 토글하는 API
    @PostMapping("/{communityId}/pin")
    public ApiResponse<String> togglePin(@PathVariable Long communityId,
                                         @AuthenticationPrincipal UserDetails userDetails) {
        communityService.togglePin(communityId, userDetails.getUsername());
        return ApiResponse.onSuccess("Pin toggled successfully");
    }

    // 핀된 게시글 조회 API
    @GetMapping("/pinned")
    public ApiResponse<List<CommunityResponseDTO.communityTitleContentDTO>> getPinnedCommunities(
            @AuthenticationPrincipal UserDetails userDetails) {
        return ApiResponse.onSuccess(communityService.getPinnedCommunities(userDetails.getUsername()));
    }

    //검색로직
    @GetMapping("/search/titles/{communityTitle}")
    public ApiResponse<List<CommunityResponseDTO.communityTitleContentDTO>> searchCommunitiesByCommunityTitle(
            @PathVariable String communityTitle){

        return ApiResponse.onSuccess(communityService.searchCommunityByTitle(communityTitle));
    }


    @GetMapping("/search/users/{username}")
    public ApiResponse<List<CommunityResponseDTO.communityTitleContentDTO>> searchCommunitiesByUsername(
            @PathVariable String username){

        return ApiResponse.onSuccess(communityService.searchCommunityByUsername(username));
    }


}
