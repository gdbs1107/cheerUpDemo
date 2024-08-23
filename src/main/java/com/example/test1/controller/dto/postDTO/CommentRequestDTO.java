package com.example.test1.controller.dto.postDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class CommentRequestDTO {


    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CommentPostRequestDTO {

        private String content;

    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CommentDTO {
        private Long id;
        private String content;
        private String username;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CommentUpdateRequestDTO {
        private String content;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CommentDeleteRequestDTO {
        private Long commentId;
    }


}
