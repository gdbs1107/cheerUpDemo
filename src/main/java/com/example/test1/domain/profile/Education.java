package com.example.test1.domain.profile;

import com.example.test1.converter.EducationConverter;
import com.example.test1.domain.UserEntity;
import com.example.test1.domain.common.BaseEntity;
import com.example.test1.domain.enumClass.Degree;
import com.example.test1.domain.enumClass.Major;
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
public class Education extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "education_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private Degree degree;
    private String schoolName;

    private LocalDate startSchool;
    private LocalDate endSchool;

    @Enumerated(EnumType.STRING)
    private Major major;
    private String majorName;

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

    public void update(Integer degreeId,Integer majorId, String schoolName,LocalDate startSchool,LocalDate endSchool,String majorName) {
        this.degree= EducationConverter.toDegree(degreeId);
        this.major = EducationConverter.toMajor(majorId);
        this.schoolName = schoolName;
        this.startSchool = startSchool;
        this.endSchool = endSchool;
        this.majorName = majorName;
    }


}
