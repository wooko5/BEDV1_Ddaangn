package com.dev.ddaangn.user;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity(name = "users")
@DynamicUpdate
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Column
    private String password;

    @Column
    private String principal;

//    @Column
//    @Enumerated(EnumType.STRING)
//    private SocialType socialType;

    @Column
    private LocalDateTime createdDate;

    @Column
    private LocalDateTime updatedDate;

//    @Builder
//    public User(String name, String password, String email, String principal,
//                SocialType socialType, LocalDateTime createdDate, LocalDateTime updatedDate) {
//        this.name = name;
//        this.password = password;
//        this.email = email;
//        this.principal = principal;
//        this.socialType = socialType;
//
//        this.createdDate = createdDate;
//        this.updatedDate = updatedDate;
//    }

    public User update(String name, String picture){
        this.name = name;
        this.picture = picture;

        return this;
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
}
