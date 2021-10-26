package com.dev.ddaangn.user;


import com.dev.ddaangn.badge.Badge;
import com.dev.ddaangn.evaluation.Evaluation;
import com.dev.ddaangn.like.Like;
import com.dev.ddaangn.post.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity(name = "users")
@DynamicUpdate
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String phoneNumber;

    private String address;

    private Double temperature;

    // 연관관계 매핑

    // User - Badge
    @OneToMany(mappedBy = "user")
    private List<Badge> badges = new ArrayList<>();

    // User - AvatarImage TODO, Image 상속받고 만들기
//    @OneToMany(mappedBy = "user")
//    private List<AvatarImage> avatarImages = new ArrayList<>();

    // User - Evaluation, 받은 평가
    @OneToMany(mappedBy = "evaluatee")
    private List<Evaluation> givenEvaluations = new ArrayList<>();

    // User가 준 평가
    @OneToMany(mappedBy = "evaluator")
    private List<Evaluation> givingEvaluations = new ArrayList<>();

    // User - Post, seller
    @OneToMany(mappedBy = "seller")
    private List<Post> soldPosts = new ArrayList<>();

    // User - Post, buyer
    @OneToMany(mappedBy = "buyer")
    private List<Post> boughtPosts = new ArrayList<>();

    // User - Like
    @OneToMany(mappedBy = "user")
    private List<Like> likes = new ArrayList<>();
}
