package com.graduationproject.backend.backendwebsiteshoe.helper;

import com.graduationproject.backend.backendwebsiteshoe.entity.CommentEntity;
import com.graduationproject.backend.backendwebsiteshoe.entity.UserInformationEntity;
import com.graduationproject.backend.backendwebsiteshoe.model.CommentModel;
import com.graduationproject.backend.backendwebsiteshoe.service.CommentService;
import com.graduationproject.backend.backendwebsiteshoe.service.UserInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * Implement helper of comment.
 *
 * @author Mai Ngoc Dinh
 */
@Component
public class CommentHelper {

  @Autowired
  CommentService commentService;

  @Autowired
  UserInformationService userInformationService;

  /**
   * Insert model.
   *
   * @param commentModel commentModel
   * @return entity.
   */
  @NonNull
  public CommentEntity insert(CommentModel commentModel) {
    UserInformationEntity userInformationEntity = userInformationService.insert(commentModel);

    return commentService.insert(commentModel, userInformationEntity.getUserInformationId());
  }

}
