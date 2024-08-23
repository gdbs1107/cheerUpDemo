package com.example.test1.converter;

import com.example.test1.controller.dto.profileDTO.EducationRequestDTO;
import com.example.test1.domain.UserEntity;
import com.example.test1.domain.enumClass.Degree;
import com.example.test1.domain.enumClass.Major;
import com.example.test1.domain.profile.Education;

public class EducationConverter {


    public static Education toEducation(EducationRequestDTO.EducationSaveRequestDTO request, UserEntity user){

        Degree degree = toDegree(request.getDegree());
        Major major = toMajor(request.getMajor());

        return Education.builder()
                .degree(degree)
                .major(major)
                .schoolName(request.getSchoolName())
                .startSchool(request.getStartSchool())
                .endSchool(request.getEndSchool())
                .majorName(request.getMajorName())
                .userEntity(user)
                .build();


    }


    public static EducationRequestDTO.EducationGetResponseDTO toResponseDTO(Education education){
        return EducationRequestDTO.EducationGetResponseDTO.builder()
                .degree(education.getDegree())
                .major(education.getMajor())
                .schoolName(education.getSchoolName())
                .startSchool(education.getStartSchool())
                .endSchool(education.getEndSchool())
                .majorName(education.getMajorName())
                .build();
    }




    //학위 이넘타입 바꾸기
    public static Degree toDegree(Integer degreeId) {
        if (degreeId == null) {
            throw new IllegalArgumentException("Invalid degreeId: " + degreeId);
        }

        switch (degreeId) {
            case 1:
                return Degree.상;
            case 2:
                return Degree.중;
            case 3:
                return Degree.하;
            default:
                throw new IllegalArgumentException("Invalid degreeId: " + degreeId); // 예외를 던지는 방법
        }




    }

    //전공 이넘타입 바꾸기
    public static Major toMajor(Integer majorId){
        if (majorId == null) {
            throw new IllegalArgumentException("Invalid majorId: " + majorId);
        }

        switch (majorId) {
            case 1:
                return Major.주전공;
            case 2:
                return Major.복수전공;
            case 3:
                return Major.이중전공;
            case 4:
                return Major.부전공;
            default:
                throw new IllegalArgumentException("Invalid majorId: " + majorId); // 예외를 던지는 방법

        }
    }
}
