package com.dev.ddaangn.badge;

import com.dev.ddaangn.image.Image;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity(name = "badge_images")
public class BadgeImage extends Image {
    @ManyToOne(fetch = FetchType.LAZY) // BadgeImage - Badge
    @JoinColumn(name = "badge", referencedColumnName = "id")
    private Badge badge;
}
