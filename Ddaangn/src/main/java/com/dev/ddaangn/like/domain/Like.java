package com.dev.ddaangn.like.domain;


import com.dev.ddaangn.common.BaseEntity;
import com.dev.ddaangn.post.domain.Post;
import com.dev.ddaangn.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity(name = "likes")
@DynamicUpdate
@AllArgsConstructor
@Builder
public class Like extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user", referencedColumnName = "id")
    private User user;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "post", referencedColumnName = "id")
    private Post post;

    public void addLike(User user, Post post) {
        this.user = user;
        this.post = post;
        this.user.addLike(this);
        this.post.addLike(this);
    }

    public void removeLike() {
        this.user.removeLike(this);
        this.post.removeLike(this);
        this.user = null;
        this.post = null;
    }
}
