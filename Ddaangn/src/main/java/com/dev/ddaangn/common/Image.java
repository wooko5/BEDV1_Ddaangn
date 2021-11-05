package com.dev.ddaangn.common;

import com.dev.ddaangn.common.BaseEntity;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@DynamicUpdate
public abstract class Image extends BaseEntity {
    private Long id;
    private String url;
    private String type;
}
