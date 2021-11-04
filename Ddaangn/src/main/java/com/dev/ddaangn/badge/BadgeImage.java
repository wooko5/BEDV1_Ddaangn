package com.dev.ddaangn.badge;

import com.dev.ddaangn.common.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "badge_images")
@Builder
public class BadgeImage extends Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "url", length = 500)
    private String url;

    @Column(name = "type", length = 30)
    private String type;

    @OneToOne(mappedBy = "badgeImage") // BadgeImage - Badge
    private Badge badge;
}
