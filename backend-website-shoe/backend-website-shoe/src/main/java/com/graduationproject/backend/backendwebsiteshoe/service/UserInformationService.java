package com.graduationproject.backend.backendwebsiteshoe.service;

import com.graduationproject.backend.backendwebsiteshoe.common.CommonService;
import com.graduationproject.backend.backendwebsiteshoe.common.Constant;
import com.graduationproject.backend.backendwebsiteshoe.common.User;
import com.graduationproject.backend.backendwebsiteshoe.entity.UserEntity;
import com.graduationproject.backend.backendwebsiteshoe.entity.UserInformationEntity;
import com.graduationproject.backend.backendwebsiteshoe.model.CommentModel;
import com.graduationproject.backend.backendwebsiteshoe.repository.UserInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implement user information service.
 *
 * @author Mai Ngoc Dinh
 */
@Service
public class UserInformationService {

  @Autowired
  CommonService commonService;

  @Autowired
  UserInformationRepository userInformationRepository;

  /**
   * Insert user with role.
   *
   * @param userInformationEntity userInformationEntity
   * @return TRUE if exist else FALSE.
   */
  public UserInformationEntity insert(UserInformationEntity userInformationEntity) {
    return userInformationRepository.save(userInformationEntity);
  }

  /**
   * Insert user with role.
   *
   * @param commentModel commentModel
   * @return entity.
   */
  public UserInformationEntity insert(CommentModel commentModel) {
    return userInformationRepository.save(this.toBuildUserInformationComment(commentModel));
  }

  /**
   * To build user information entity.
   *
   * @param commentModel commentModel
   * @return entity.
   */
  private UserInformationEntity toBuildUserInformationComment(CommentModel commentModel) {
    UserInformationEntity entity = new UserInformationEntity();
    entity.setAddress(Constant.EMPTY_SPACE);
    entity.setEmail(commentModel.getCommentEmail());
    entity.setLastName(commentModel.getCommentName());
    entity.setPhone(Constant.EMPTY_SPACE);
    entity.setUserCode(User.CUSTOMER.getCode());

    commonService.setCommonCreatedEntity(entity);
    return entity;
  }
}
