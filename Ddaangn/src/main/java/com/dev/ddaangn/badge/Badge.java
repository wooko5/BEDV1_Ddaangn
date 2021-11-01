package com.dev.ddaangn.badge;


import com.dev.ddaangn.common.BaseEntity;
import com.dev.ddaangn.user.User;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@DynamicUpdate
@Entity(name = "badges")
public class Badge extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "description", length = 200)
    private String description;

    @Column(name = "achievement")
    private boolean achievement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "badge")
    private List<BadgeImage> badgeImages = new ArrayList<>();

}
