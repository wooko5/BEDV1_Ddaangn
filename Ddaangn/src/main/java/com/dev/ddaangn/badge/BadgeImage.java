package com.dev.ddaangn.badge;

import com.dev.ddaangn.image.Image;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "badge_images")
public class BadgeImage extends Image {

    @OneToOne(mappedBy = "badgeImage") // BadgeImage - Badge
    @JoinColumn(name = "badge_id")
    private Badge badge;

    public BadgeImage(Long id, String url, String type, Badge badge) {
        super(id, url, type);
        this.badge = badge;
    }
}
