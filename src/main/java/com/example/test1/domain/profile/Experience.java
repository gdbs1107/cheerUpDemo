package com.example.test1.domain.profile;

import com.example.test1.domain.UserEntity;
import com.example.test1.domain.common.BaseEntity;
import com.example.test1.domain.enumClass.ExperienceCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Experience extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "experience_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private ExperienceCategory experienceCategory;

    private String expName;
    private LocalDate startExp;
    private LocalDate endExp;

    private String topicExp;
    private String contentExp;
    private String linkExp;

    @OneToOne
    @JoinColumn(name = "user_entity_id")
    private UserEntity userEntity;


    public void setUserEntity(UserEntity userEntity) {
        if (userEntity != null) {
            return;
        }else {
            this.userEntity = userEntity;
        }
    }


    public void update(ExperienceCategory experienceCategory, String expName, String topicExp, String contentExp, String linkExp, LocalDate startExp, LocalDate endExp) {
        this.experienceCategory = experienceCategory;
        this.expName = expName;
        this.topicExp = topicExp;
        this.contentExp = contentExp;
        this.linkExp = linkExp;
        this.startExp = startExp;
        this.endExp = endExp;
    }
}
