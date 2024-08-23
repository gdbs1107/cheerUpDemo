package com.example.test1.converter;

import com.example.test1.controller.dto.postDTO.CommentRequestDTO;
import com.example.test1.controller.dto.postDTO.CommunityRequestDTO;
import com.example.test1.controller.dto.postDTO.CommunityResponseDTO;
import com.example.test1.domain.UserEntity;
import com.example.test1.domain.postEntity.Community;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CommunityConverter {


    public static Community toCommunity(CommunityRequestDTO.CommunityPostRequestDTO requestDTO, UserEntity user){

        //좋아요 수 0으로 초기화
        return Community.builder()
                .title(requestDTO.getTitle())
                .content(requestDTO.getContent())
                .likeCount(0)
                .viewCount(0)
                .pinnedCount(0)
                .userEntity(user)
                .build();
    }

    public static CommunityResponseDTO.communitySearchResponseDTO toCommunityResponseDTO(Community community,Integer likeCount) {


        //리스트로 코멘트를 전환해서 무한참조 방지
        List<CommentRequestDTO.CommentDTO> commentDTOs = community.getComments().stream()
                .map(comment -> CommentRequestDTO.CommentDTO.builder()
                        .id(comment.getId())
                        .content(comment.getContent())
                        .username(comment.getUserEntity().getUsername()) // 사용자 이름 설정
                        .build())
                .collect(Collectors.toList());

        return CommunityResponseDTO.communitySearchResponseDTO.builder()
                .id(community.getId()) // ID를 추가해야 합니다.
                .title(community.getTitle())
                .content(community.getContent())
                .likeCount(likeCount)
                .comments(commentDTOs) // 댓글 리스트를 포함
                .build();
    }

    public static CommunityResponseDTO.communityTitleContentDTO toCommunityTitleContentDTO(Community community,Long commentCount,String username){

        return CommunityResponseDTO.communityTitleContentDTO.builder()
                .title(community.getTitle())
                .content(community.getContent())
                .likeCount(community.getLikeCount())
                .commentsCount(commentCount)
                .username(username)
                .viewCount(community.getViewCount())
                .build();

    }
}
