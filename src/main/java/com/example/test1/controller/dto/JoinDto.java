package com.example.test1.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;


public class JoinDto {


    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class JoinRequestDTO{

        @NotNull(message = "null값입니다")
        private String username;

        @NotNull(message = "null값입니다")
        private String password;

    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LoginRequestDto{

        private String username;
        private String password;

    }


}
