package com.graduationproject.backend.backendwebsiteshoe.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@IdClass(TrademarkEntityKey.class)
public class TrademarkEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    public TrademarkEntity() {

        super();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trademark_id", nullable = false)
    private Long trademarkId;

    @Id
    @Column(name = "category_id", nullable = false)
    private String categoryId;

    @Column(name = "trademark_name", nullable = false)
    private String trademarkName;

    @Column(name = "seo", nullable = false)
    private String seo;

}
