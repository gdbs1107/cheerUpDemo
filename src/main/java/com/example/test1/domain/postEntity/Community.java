package com.example.test1.domain.postEntity;

import com.example.test1.domain.UserEntity;
import com.example.test1.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Community extends BaseEntity {
//치얼로그 엔티티


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "community_id")
    private Long id;

    private String title;
    private String content;
    private Integer likeCount;
    private Integer viewCount;
    private Integer pinnedCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_entity_id")
    private UserEntity userEntity;

    @OneToMany(mappedBy = "community",cascade = CascadeType.ALL,orphanRemoval = true)
    @BatchSize(size = 20)
    private Set<Likes> likes=new HashSet<>();

    @OneToMany(mappedBy = "community",cascade = CascadeType.ALL,orphanRemoval = true)
    @BatchSize(size = 20)
    private Set<Comment> comments=new HashSet<>();




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

    public void addView() {
        this.viewCount = this.viewCount + 1;
    }

    public void addPinCount() {
        this.pinnedCount = this.pinnedCount + 1;
    }
    public void removePinCount() {
        this.pinnedCount = this.pinnedCount -1;
    }



}
