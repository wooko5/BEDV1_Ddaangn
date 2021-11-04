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

    /*Badge와 BadgeImage는 단방향 관계여야 BadgeControllerTest의 getAllTest()에서 무한참조에 빠지지 않는다
    그래서 아래의 코드를 주석처리함
    @OneToOne(mappedBy = "badgeImage")
    private Badge badge;
     */
}
