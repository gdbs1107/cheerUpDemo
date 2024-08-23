package com.example.test1.domain.profile;

import com.example.test1.domain.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BasicProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private LocalDate birthDate;
    private Integer phoneNumber;
    private String email;


    private String imagePath;
    private String imageName;


    @OneToOne
    @JoinColumn(name = "user_entity_id")
    private UserEntity userEntity;


    public void setUserEntity(UserEntity userEntity) {
        if (userEntity != null) {
            this.userEntity = userEntity;
        }else {
            return;
        }
    }

    public void setImagePath(String imagePath) {
        if (imagePath != null) {
            this.imagePath = imagePath;
        }else {
            return;
        }
    }

    public void setImageName(String imageName) {
        if (imageName != null) {
            this.imageName = imageName;
        }else {
            return;
        }
    }


    //연관관계 & 수정메서드들
    public void update(String name, LocalDate birthDate, Integer phoneNumber, String email, String imagePath,String imageName) {
        this.name = name;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.imageName = imageName;
        this.imagePath = imagePath;
    }
}