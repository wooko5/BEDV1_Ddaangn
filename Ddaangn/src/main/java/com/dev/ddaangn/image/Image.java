package com.dev.ddaangn.image;

import com.dev.ddaangn.common.BaseEntity;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@DynamicUpdate
@Entity(name = "images")
public class Image extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "url", length = 500)
    private String url;

    @Column(name = "type", length = 30) // 이미지의 종류를 구분하기 위한 속성
    private String type;
}
