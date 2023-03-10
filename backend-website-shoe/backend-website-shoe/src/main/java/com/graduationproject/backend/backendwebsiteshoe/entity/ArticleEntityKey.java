package com.graduationproject.backend.backendwebsiteshoe.entity;

import jakarta.persistence.Column;
import lombok.Data;

import java.io.Serializable;

@Data
public class ArticleEntityKey implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "article_id", nullable = false)
    private Long articleId;

    public ArticleEntityKey setPk(Long productId, Long articleId) {
        setProductId(productId);
        setArticleId(articleId);
        return this;
    }
}
