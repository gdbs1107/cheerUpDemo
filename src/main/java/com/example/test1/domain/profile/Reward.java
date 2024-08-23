package com.example.test1.domain.profile;

import com.example.test1.domain.UserEntity;
import com.example.test1.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reward extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reward_id")
    private Long id;

    private String rewardName;
    private String publisher;
    private LocalDate publishDate;
    private String authentication;

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

    public void update(String rewardName, String publisher, LocalDate publishDate, String authentication) {
        this.rewardName = rewardName;
        this.publisher = publisher;
        this.publishDate = publishDate;
        this.authentication = authentication;
    }
}
