package com.example.test1.domain.postEntity;

import com.example.test1.domain.UserEntity;
import com.example.test1.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class PostPortfolio extends BaseEntity {
//해당 엔티티는 커뮤니티와 통합되었습니다


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "postPortfolio_id")
    private Long id;

    private String title;
    private String content;
    private Integer likeCount=0;

    //포트폴리오 링크가 추가되어야함

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_entity_id")
    private UserEntity userEntity;

    @OneToMany(mappedBy = "postPortfolio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "postPortfolio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Likes> likes = new ArrayList<>();


    //연관관계 & 수정메서드들
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void addLike() {
        this.likeCount = this.likeCount == null ? 1 : this.likeCount + 1;
    }

    public void removeLike() {
        if (this.likeCount != null && this.likeCount > 0) {
            this.likeCount--;
        }
    }


}
