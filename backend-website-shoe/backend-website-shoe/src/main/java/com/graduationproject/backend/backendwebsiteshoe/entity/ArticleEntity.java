package com.graduationproject.backend.backendwebsiteshoe.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@IdClass(ArticleEntityKey.class)
public class ArticleEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    public ArticleEntity() {

        super();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id", nullable = false)
    private Long articleId;

    @Column(name = "article_name", nullable = false)
    private String articleName;

    @Column(name = "article_description", nullable = false)
    private String articleDescription;

    @Column(name = "article_seo", nullable = false)
    private String articleSeo;
}
