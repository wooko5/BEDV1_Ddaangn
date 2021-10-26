package com.dev.ddaangn.image;

import com.dev.ddaangn.common.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity(name = "images")
@DynamicUpdate
@MappedSuperclass
public abstract class Image extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "url", length = 500)
    private String url;
}
