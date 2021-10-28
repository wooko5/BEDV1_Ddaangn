package com.dev.ddaangn.badge;

import com.dev.ddaangn.image.Image;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity(name = "badge_images")
public class BadgeImage extends Image {

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    @Column(name = "url", length = 500)
//    private String url;

    // BadgeImage - Badge
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "badge", referencedColumnName = "id")
    private Badge badge;
}
