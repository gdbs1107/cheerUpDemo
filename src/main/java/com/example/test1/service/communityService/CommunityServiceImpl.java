package com.example.test1.service.communityService;

import com.example.test1.Repository.*;
import com.example.test1.Repository.postRepository.CommentRepository;
import com.example.test1.Repository.postRepository.CommunityRepository;
import com.example.test1.Repository.postRepository.LikeRepository;
import com.example.test1.Repository.postRepository.PinnedCommunityRepository;
import com.example.test1.controller.dto.postDTO.CommunityRequestDTO;
import com.example.test1.controller.dto.postDTO.CommunityResponseDTO;
import com.example.test1.converter.CommunityConverter;
import com.example.test1.domain.UserEntity;
import com.example.test1.domain.postEntity.Community;
import com.example.test1.domain.postEntity.Likes;
import com.example.test1.domain.postEntity.PinnedCommunity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CommunityServiceImpl implements CommunityService {

    private final CommunityRepository communityRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final LikeRepository likesRepository;
    private final PinnedCommunityRepository pinnedCommunityRepository;


    @Override
    public void postCommunity(CommunityRequestDTO.CommunityPostRequestDTO request, String username){

        UserEntity user = userRepository.findByUsername(username).orElseThrow(
                ()->new RuntimeException("user not found"));

        Community newCommunity = CommunityConverter.toCommunity(request,user);
        communityRepository.save(newCommunity);

    }

    @Override
    public void updateCommunity(Long communityId, CommunityRequestDTO.CommunityPostRequestDTO request, String username){

        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Find the community post that is mapped to the user
        Community existingCommunity = communityRepository.findByIdAndUserEntity(communityId, user)
                .orElseThrow(() -> new RuntimeException("Community not found or user not authorized"));

        // Update the community post
        existingCommunity.update(request.getTitle(), request.getContent());

        communityRepository.save(existingCommunity);

    }

    @Override
    public void deleteCommunity(Long communityId, String username){

        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Community existingCommunity = communityRepository.findById(communityId)
                .orElseThrow(() -> new RuntimeException("Community not found"));

        userValidate(existingCommunity, user);

        communityRepository.delete(existingCommunity);
    }




    //---------조회로직---------------

    @Override
    public CommunityResponseDTO.communitySearchResponseDTO searchCommunity(Long communityId) {

        // 커뮤니티와 사용자 정보를 페치합니다.
        Community community = communityRepository.findById(communityId)
                .orElseThrow(() -> new RuntimeException("Community not found"));

        // 조회 수를 증가시키고, 데이터베이스에 반영합니다.
        community.addView();
        communityRepository.save(community);

        // 커뮤니티와 관련된 좋아요 수와 댓글 수를 별도로 조회합니다.
        Integer likeCount = likesRepository.countByCommunityId(communityId);

        // DTO로 변환하여 반환합니다.
        return CommunityConverter.toCommunityResponseDTO(community, likeCount);
    }


    //길게 제목,내용,댓글 수, 좋아요 수 를 반환하는 서비스로직 -> 전체조회 시 이용될 예정
    @Override
    public CommunityResponseDTO.communityTitleContentDTO communityTitleContent(Long communityId){

        Community community = communityRepository.findById(communityId)
                .orElseThrow(() -> new RuntimeException("Community not found"));

        UserEntity userEntity = community.getUserEntity();
        String username = userEntity.getUsername();
        Long commentCount = commentRepository.countByCommunityId(communityId);

        return CommunityConverter.toCommunityTitleContentDTO(community,commentCount,username);

    }

    @Override
    public Page<CommunityResponseDTO.communityTitleContentDTO> searchAllCommunities(Pageable pageable) {
        return communityRepository.findAll(pageable)
                .map(community -> {
                    UserEntity userEntity = community.getUserEntity();
                    String username = userEntity.getUsername();
                    Long commentCount = commentRepository.countByCommunityId(community.getId());
                    return CommunityConverter.toCommunityTitleContentDTO(community, commentCount,username);
                });
    }


    @Override
    public void toggleLike(Long communityId, String username) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Community community = communityRepository.findById(communityId)
                .orElseThrow(() -> new RuntimeException("Community not found"));

        Optional<Likes> existingLike = likesRepository.findByCommunityAndUserEntity(community, user);

        if (existingLike.isPresent()) {
            // Remove like
            likesRepository.delete(existingLike.get());
            community.removeLike();
        } else {
            // Add like
            likesRepository.save(Likes.builder().community(community).userEntity(user).build());
            community.addLike();
        }

        communityRepository.save(community);
    }


    //조회수로 정렬해서 뽑아주는 로직
    @Override
    public Page<CommunityResponseDTO.communityTitleContentDTO> searchAllCommunitiesByViewCount(PageRequest pageRequest) {
        return communityRepository.findAllByOrderByViewCountDesc(pageRequest)
                .map(community -> {
                    UserEntity userEntity = community.getUserEntity();
                    String username = userEntity.getUsername();
                    Long commentCount = commentRepository.countByCommunityId(community.getId());
                    return CommunityConverter.toCommunityTitleContentDTO(community, commentCount, username);
                });
    }


    // 핀 기능 추가
    @Override
    public void togglePin(Long communityId, String username) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Community community = communityRepository.findById(communityId)
                .orElseThrow(() -> new RuntimeException("Community not found"));

        Optional<PinnedCommunity> existingPin = pinnedCommunityRepository.findByUserEntityAndCommunity(user, community);

        if (existingPin.isPresent()) {
            // 핀 제거
            pinnedCommunityRepository.delete(existingPin.get());
            community.removePinCount();
        } else {
            // 핀 추가
            pinnedCommunityRepository.save(PinnedCommunity.create(user, community));
            community.addPinCount();

        }
    }

    // 핀된 게시글 조회
    @Override
    public List<CommunityResponseDTO.communityTitleContentDTO> getPinnedCommunities(String username) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<PinnedCommunity> pinnedCommunities = pinnedCommunityRepository.findAllByUserEntity(user);

        return pinnedCommunities.stream()
                .map(pinnedCommunity -> {
                    Community community = pinnedCommunity.getCommunity();
                    Long commentCount = commentRepository.countByCommunityId(community.getId());
                    return CommunityConverter.toCommunityTitleContentDTO(community, commentCount, user.getUsername());
                })
                .collect(Collectors.toList());
    }



    //게시글 검색기능 (이전에 조회기능의 서비스이름을 잘못지어서 큰일이네...)
    @Override
    public List<CommunityResponseDTO.communityTitleContentDTO> searchCommunityByTitle(String title){

        return communityRepository.searchCommunitiesByTitle(title)
                .stream().map(community ->{
                    UserEntity userEntity = community.getUserEntity();
                    Long commentCount=commentRepository.countByCommunityId(community.getId());
                    return CommunityConverter.toCommunityTitleContentDTO(community,commentCount,userEntity.getUsername());
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<CommunityResponseDTO.communityTitleContentDTO> searchCommunityByUsername(String username){

        // 작성자명을 기반으로 커뮤니티 게시글을 검색
        return communityRepository.searchCommunitiesByUsername(username)
                .stream().map(community -> {
                    // community 객체에서 작성자 정보를 가져옴
                    UserEntity userEntity = community.getUserEntity();
                    Long commentCount = commentRepository.countByCommunityId(community.getId());
                    return CommunityConverter.toCommunityTitleContentDTO(community, commentCount, userEntity.getUsername());
                })
                .collect(Collectors.toList());
    }








    private static void userValidate(Community existingCommunity, UserEntity user) {
        if (!existingCommunity.getUserEntity().equals(user)) {
            throw new RuntimeException("is not not yours");
        }
    }
}
