package com.example.test1.domain;

import com.example.test1.domain.common.BaseEntity;
import com.example.test1.domain.enumClass.Gender;
import com.example.test1.domain.postEntity.*;
import com.example.test1.domain.profile.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_entity_id")
    private Long id;

    private String username;
    private String password;
    private String role;


    //추가적으로 받아야할 개인정보
    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Integer age;
    private String email;


    //따로 필요한 포인트: 시작 0으로 초기화
    private Integer point;

    @OneToMany(mappedBy = "userEntity",cascade = CascadeType.ALL)
    private List<Community> communities=new ArrayList<>();


    @OneToMany(mappedBy = "userEntity")
    private List<PostPortfolio> postPortfolios=new ArrayList<>();

    @OneToOne(mappedBy = "userEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private BasicProfile basicProfile;

    @OneToOne(mappedBy = "userEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private Education education;

    @OneToOne(mappedBy = "userEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private Experience experience;

    @OneToOne(mappedBy = "userEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private Link link;


    @OneToOne(mappedBy = "userEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private Reward reward;

    @OneToOne(mappedBy = "userEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private Tech tech;

    @OneToOne(mappedBy = "userEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private Career career;



    public void setBasicProfile(BasicProfile basicProfile) {
        if (basicProfile != null) {
            return;
        }else {
            this.basicProfile = basicProfile;
        }
    }

    public void removeBasicProfile() {
        if (this.basicProfile != null) {
            this.basicProfile.setUserEntity(null);
            this.basicProfile = null;
        }
    }

    public void removeEducation() {
        if (this.education != null) {
            this.education.setUserEntity(null);
            this.education = null;
        }
    }

    public void removeExperience() {
        if (this.experience != null) {
            this.experience.setUserEntity(null);
            this.experience = null;
        }
    }

    public void removeLink() {
        if (this.link != null) {
            this.link.setUserEntity(null);
            this.link = null;
        }
    }

    public void removeReward() {
        if (this.reward != null) {
            this.reward.setUserEntity(null);
            this.reward = null;
        }
    }

    public void removeTech() {
        if (this.tech != null) {
            this.tech.setUserEntity(null);
            this.tech = null;
        }
    }

    public void removeCareer() {
        if (this.career != null) {
            this.career.setUserEntity(null);
            this.career = null;
        }
    }

}
