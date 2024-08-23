package com.example.test1.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.File;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageUploadDTO {
    private String newFileName;
    private String url;
}