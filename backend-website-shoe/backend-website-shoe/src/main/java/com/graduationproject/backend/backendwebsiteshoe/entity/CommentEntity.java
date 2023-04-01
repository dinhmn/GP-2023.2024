package com.graduationproject.backend.backendwebsiteshoe.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Comment entity.
 *
 * @author Mai Ngoc Dinh.
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@IdClass(CommentEntityKey.class)
@Table(name = "tbl_comment")
public class CommentEntity extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  public CommentEntity() {

    super();
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "comment_id", nullable = false)
  private Long commentId;

  @Id
  @Column(name = "user_id", nullable = false)
  private Long userId;

  @Column(name = "product_id", nullable = false)
  private Long productId;

  @Column(name = "article_id")
  private Long articleId;

  @Column(name = "comment_detail", nullable = false)
  private String comment_detail;

  /*
   * Code = 01 => Product
   * Code = 02 => Article
   * */
  @Column(name = "comment_code", length = 2, nullable = false)
  private String commentCode;
}
