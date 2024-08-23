package com.example.test1.service.communityService;

import com.example.test1.controller.dto.postDTO.CommunityRequestDTO;
import com.example.test1.controller.dto.postDTO.CommunityResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommunityService {


    void postCommunity(CommunityRequestDTO.CommunityPostRequestDTO request, String username);

    void updateCommunity(Long communityId, CommunityRequestDTO.CommunityPostRequestDTO request, String username);

    void deleteCommunity(Long communityId, String username);

    CommunityResponseDTO.communitySearchResponseDTO searchCommunity(Long communityId);

    CommunityResponseDTO.communityTitleContentDTO communityTitleContent(Long communityId);

    Page<CommunityResponseDTO.communityTitleContentDTO> searchAllCommunities(Pageable pageable);

    void toggleLike(Long communityId, String username);


    //조회수로 정렬해서 뽑아주는 로직
    Page<CommunityResponseDTO.communityTitleContentDTO> searchAllCommunitiesByViewCount(PageRequest pageRequest);

    // 핀 기능 추가
    void togglePin(Long communityId, String username);

    // 핀된 게시글 조회
    List<CommunityResponseDTO.communityTitleContentDTO> getPinnedCommunities(String username);

    //게시글 검색기능 (이전에 조회기능의 서비스이름을 잘못지어서 큰일이네...)
    List<CommunityResponseDTO.communityTitleContentDTO> searchCommunityByTitle(String title);

    List<CommunityResponseDTO.communityTitleContentDTO> searchCommunityByUsername(String username);
}
