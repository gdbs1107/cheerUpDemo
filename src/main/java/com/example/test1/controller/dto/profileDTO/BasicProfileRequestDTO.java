package com.example.test1.controller.dto.profileDTO;

import jakarta.validation.constraints.Email;
import lombok.Getter;

import java.time.LocalDate;

public class BasicProfileRequestDTO {


    @Getter
    public static class BasicProfileSaveRequestDTO {
        private String name;
        private LocalDate birthDate;
        private Integer phoneNumber;

        @Email
        private String email;
        private String photoName;
    }
}