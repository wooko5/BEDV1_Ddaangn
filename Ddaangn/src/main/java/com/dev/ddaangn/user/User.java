package com.dev.ddaangn.user;


import com.dev.ddaangn.badge.Badge;
import com.dev.ddaangn.common.BaseEntity;
import com.dev.ddaangn.evaluation.Evaluation;
import com.dev.ddaangn.like.Like;
import com.dev.ddaangn.post.domain.Post;
import com.dev.ddaangn.user.vo.BoughtPosts;
import com.dev.ddaangn.user.vo.SoldPosts;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "users")
@DynamicUpdate
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
    private Role role;


    @Builder
    public User(String name, String email, String picture, Role role) {
        this.name = name;
        this.email = email;
        this.picture=picture;
        this.role=role;
    }



    public User update(String name, String picture){
        this.name = name;
        this.picture = picture;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }

    private Double temperature;

    // 연관관계 매핑

    // User - Badge
//    @OneToMany(mappedBy = "user")
//    private List<Badge> badges = new ArrayList<>();
//
//    // User - AvatarImage TODO, Image 상속받고 만들기
////    @OneToMany(mappedBy = "user")
////    private List<AvatarImage> avatarImages = new ArrayList<>();
//
//    // User - Evaluation, 받은 평가
//    @OneToMany(mappedBy = "evaluatee")
//    private List<Evaluation> givenEvaluations = new ArrayList<>();
//
//    // User가 준 평가
//    @OneToMany(mappedBy = "evaluator")
//    private List<Evaluation> givingEvaluations = new ArrayList<>();
//
//    // User - Post, seller
//    @OneToMany(mappedBy = "seller")
//    private List<Post> soldPosts = new ArrayList<>();
//
//    // User - Post, buyer
//    @OneToMany(mappedBy = "buyer")
//    private List<Post> boughtPosts = new ArrayList<>();
//
//    // User - Like
//    @OneToMany(mappedBy = "user")
//    private List<Like> likes = new ArrayList<>();

    // User - Post, seller
    @Embedded
    private SoldPosts soldPosts;

    // User - Post, buyer
    @Embedded
    private BoughtPosts boughtPosts;

}
