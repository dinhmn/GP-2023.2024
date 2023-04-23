package com.graduationproject.backend.backendwebsiteshoe.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Create comment model.
 *
 * @author Mai Ngoc Dinh
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentModel {

  private Long productId;

  private String commentDetail;

  private String commentName;

  private String commentEmail;

}
