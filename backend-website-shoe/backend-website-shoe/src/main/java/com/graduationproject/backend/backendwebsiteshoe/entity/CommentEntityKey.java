package com.graduationproject.backend.backendwebsiteshoe.entity;

import jakarta.persistence.Column;
import java.io.Serializable;
import lombok.Data;

/**
 * Comment entity key.
 *
 * @author Mai Ngoc Dinh.
 */
@Data
public class CommentEntityKey implements Serializable {

  private static final long serialVersionUID = 1L;

  @Column(name = "comment_id", nullable = false)
  private Long commentId;

  @Column(name = "userId", nullable = false)
  private Long userId;

  /**
   * Set PK.
   *
   * @param commentId commentId
   * @param userId    userId
   * @return key.
   */
  public CommentEntityKey setPk(Long commentId, Long userId) {
    setCommentId(commentId);
    setUserId(userId);
    return this;
  }
}
