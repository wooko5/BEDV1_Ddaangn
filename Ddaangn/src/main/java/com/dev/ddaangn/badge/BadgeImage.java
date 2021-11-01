package com.dev.ddaangn.badge;

import com.dev.ddaangn.image.Image;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Entity(name = "badge_images")
public class BadgeImage extends Image {
    @ManyToOne(fetch = FetchType.LAZY) // BadgeImage - Badge
    @JoinColumn(name = "badge", referencedColumnName = "id")
    private Badge badge;

    public void setBadge(Badge badge){
        if(Objects.nonNull(this.badge)){
            this.badge.getBadgeImages().remove(this);
        }
        this.badge = badge;
        badge.getBadgeImages().add(this);
    }
}
