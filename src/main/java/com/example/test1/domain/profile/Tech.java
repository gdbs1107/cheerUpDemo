package com.example.test1.domain.profile;

import com.example.test1.domain.UserEntity;
import com.example.test1.domain.common.BaseEntity;
import com.example.test1.domain.enumClass.TechLevel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Profile;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tech extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tech_id")
    private Long id;

    private String techName;
    private TechLevel techLevel;

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

    public void update(String techName, TechLevel techLevel) {
        this.techName = techName;
        this.techLevel = techLevel;
    }
}
