package com.dev.ddaangn.user;


import com.dev.ddaangn.badge.Badge;
import com.dev.ddaangn.common.BaseEntity;
import com.dev.ddaangn.evaluation.domain.Evaluation;
import com.dev.ddaangn.like.Like;
import com.dev.ddaangn.user.role.LoginRole;
import com.dev.ddaangn.user.vo.BoughtPosts;
import com.dev.ddaangn.user.vo.SoldPosts;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "users")
@DynamicUpdate
@Builder
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LoginRole role;

    @Setter
    @Column(nullable = true)
    private Double temperature;

    private String address;

    @Column(nullable = true)
    private String phoneNumber;

    // User - Post, seller
    @Embedded
    private SoldPosts soldPosts;

    // User - Post, buyer
    @Embedded
    private BoughtPosts boughtPosts;
    // 연관관계 매핑
    // User - Badge
    @OneToMany(mappedBy = "user")
    private List<Badge> badges = new ArrayList<>();
    // User - Evaluation, 받은 평가
    @OneToMany(mappedBy = "evaluated", cascade = CascadeType.ALL)
    private List<Evaluation> givenEvaluations = new ArrayList<>();
    // User가 준 평가
    @OneToMany(mappedBy = "evaluator", cascade = CascadeType.ALL)
    private List<Evaluation> givingEvaluations = new ArrayList<>();
    // User - Like
    @OneToMany(mappedBy = "user")
    private List<Like> likes = new ArrayList<>();

    @Builder
    public User(String name, String email, String picture, LoginRole role) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public User update(String name, String picture) {
        this.name = name;
        this.picture = picture;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }

    public void addEvaluated(Evaluation evaluation) {
        givenEvaluations.add(evaluation);
    }

    public void addBadges(Badge badge) {
        this.badges.add(badge);
    }

    public void removeBadges(Badge badge) {
        this.badges.remove(badge);
    }

    public void addEvaluator(Evaluation evaluation) {
        givingEvaluations.add(evaluation);
    }
}
