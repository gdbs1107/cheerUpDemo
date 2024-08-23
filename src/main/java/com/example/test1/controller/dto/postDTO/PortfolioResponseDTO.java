package com.example.test1.controller.dto.postDTO;

import com.example.test1.domain.postEntity.PostPortfolio;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.List;

public class PortfolioResponseDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PortfolioSearchResponseDTO {

        private Long id;
        private String content;
        private String title;
        private Integer likeCount;
        private List<CommentRequestDTO.CommentDTO> comments;

    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PortfolioSearchAllResponseDTO {

        private List<PostPortfolio> postPortfolios;
    }


    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PortfolioTitleContentDTO{

        //길다랗게 제목이랑 내용만 조회하는 DTO
        private String title;
        private String content;
        private Integer likeCount;
        private Long commentsCount;

        @Nullable
        private String username;

    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PortfolioPageResponseDTO {

        private int totalPages;
        private long totalElements;
        private List<PortfolioResponseDTO.PortfolioTitleContentDTO> content;
    }
}
