package com.example.test1.domain.profile;

import com.example.test1.domain.UserEntity;
import com.example.test1.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Link extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "link_id")
    private Long id;

    private String address;

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

    public void update(String address) {
        this.address = address;
    }
}
