package com.graduationproject.backend.backendwebsiteshoe.service;

import com.graduationproject.backend.backendwebsiteshoe.common.CommonService;
import com.graduationproject.backend.backendwebsiteshoe.dto.IComment;
import com.graduationproject.backend.backendwebsiteshoe.entity.CommentEntity;
import com.graduationproject.backend.backendwebsiteshoe.model.CommentModel;
import com.graduationproject.backend.backendwebsiteshoe.repository.CommentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implement service of comment.
 *
 * @author Mai Ngoc Dinh.
 */
@Service
public class CommentService {

  private static final String COMMENT_PRODUCT = "01";

  @Autowired
  CommonService commonService;

  @Autowired
  CommentRepository commentRepository;

  /**
   * Get all comment of product.
   *
   * @param productId productId
   * @return list of comment.
   */
  public List<IComment> getAll(Long productId) {
    return commentRepository.findAllCommentByProductId(productId);
  }

  /**
   * Insert comment entity.
   *
   * @param commentModel commentModel
   * @param userId userId
   * @return insert comment.
   */
  public CommentEntity insert(CommentModel commentModel, Long userId) {
    return commentRepository.save(this.toBuildComment(commentModel, userId));
  }

  /**
   * To build comment entity.
   *
   * @param commentModel commentModel
   * @param userId userId
   * @return comment entity.
   */
  private CommentEntity toBuildComment(CommentModel commentModel, Long userId) {
    CommentEntity commentEntity = new CommentEntity();
    commentEntity.setUserInformationId(userId);
    commentEntity.setProductId(commentModel.getProductId());
    commentEntity.setCommentCode(COMMENT_PRODUCT);
    commentEntity.setCommentDetail(commentModel.getCommentDetail());
    commonService.setCommonCreatedEntity(commentEntity);
    return commentEntity;
  }

}
