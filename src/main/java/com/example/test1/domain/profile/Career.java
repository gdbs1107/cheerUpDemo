package com.example.test1.domain.profile;

import com.example.test1.domain.UserEntity;
import com.example.test1.domain.common.BaseEntity;
import com.example.test1.domain.enumClass.TechLevel;
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
public class Career extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "career_id")
    private Long id;

    private String companyName;
    private LocalDate startComp;
    private LocalDate endComp;

    //직급(이사,대리 이런거)
    private String position;

    //직무(담당업무랑 솔지깋 뭐가 다르닞 모르겠긴함)
    private String companyFunction;

    //담당업무 소개
    private String duties;

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

    public void update(String companyName, LocalDate startComp, LocalDate endComp, String position, String companyFunction, String duties) {
        this.companyName=companyName;
        this.startComp = startComp;
        this.endComp = endComp;
        this.position = position;
        this.companyFunction = companyFunction;
        this.duties = duties;
    }
}
