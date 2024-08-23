package com.example.test1.converter;

import com.example.test1.controller.dto.postDTO.CommentRequestDTO;
import com.example.test1.controller.dto.postDTO.PortfolioResponseDTO;
import com.example.test1.controller.dto.postDTO.PostPortfolioRequestDTO;
import com.example.test1.domain.UserEntity;
import com.example.test1.domain.postEntity.PostPortfolio;

import java.util.List;
import java.util.stream.Collectors;

public class PostPortfolioConverter {

    public static PostPortfolio toPostPortfolio(PostPortfolioRequestDTO.postPortfolioRequestDTO request, UserEntity user) {

        return PostPortfolio.builder()
                .content(request.getContent())
                .title(request.getTitle())
                .userEntity(user)
                .likeCount(0)
                .build();
    }

    public static PortfolioResponseDTO.PortfolioSearchResponseDTO toPortfolioSearchResponseDTO(PostPortfolio postPortfolio) {


        //리스트로 코멘트를 전환해서 무한참조 방지
        List<CommentRequestDTO.CommentDTO> commentDTOs = postPortfolio.getComments().stream()
                .map(comment -> CommentRequestDTO.CommentDTO.builder()
                        .id(comment.getId())
                        .content(comment.getContent())
                        .username(comment.getUserEntity().getUsername()) // 사용자 이름 설정
                        .build())
                .collect(Collectors.toList());

        return PortfolioResponseDTO.PortfolioSearchResponseDTO.builder()
                .id(postPortfolio.getId()) // ID를 추가해야 합니다.
                .title(postPortfolio.getTitle())
                .content(postPortfolio.getContent())
                .likeCount(postPortfolio.getLikeCount())
                .comments(commentDTOs) // 댓글 리스트를 포함
                .build();
    }

    public static PortfolioResponseDTO.PortfolioTitleContentDTO toportfolioTitleContentDTO(PostPortfolio portfolio,Long commentCount,String username){

        return PortfolioResponseDTO.PortfolioTitleContentDTO.builder()
                .title(portfolio.getTitle())
                .content(portfolio.getContent())
                .likeCount(portfolio.getLikeCount())
                .commentsCount(commentCount)
                .username(username)
                .build();

    }
}
