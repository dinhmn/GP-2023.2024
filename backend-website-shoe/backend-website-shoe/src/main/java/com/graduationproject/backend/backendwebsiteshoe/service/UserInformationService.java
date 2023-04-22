package com.graduationproject.backend.backendwebsiteshoe.service;

import com.graduationproject.backend.backendwebsiteshoe.entity.UserEntity;
import com.graduationproject.backend.backendwebsiteshoe.entity.UserInformationEntity;
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
}
