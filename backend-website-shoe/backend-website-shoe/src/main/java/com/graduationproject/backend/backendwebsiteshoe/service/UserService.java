package com.graduationproject.backend.backendwebsiteshoe.service;

import com.graduationproject.backend.backendwebsiteshoe.entity.ArticleEntity;
import com.graduationproject.backend.backendwebsiteshoe.entity.UserEntity;
import com.graduationproject.backend.backendwebsiteshoe.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implement service of user.
 *
 * @author Mai Ngoc Dinh
 */
@Service
public class UserService {

  @Autowired
  UserRepository userRepository;

  /**
   * Get one user.
   *
   * @param username username
   * @return user entity.
   */
  public Optional<UserEntity> getArticleByArticle(String username) {
    return userRepository.findByUsername(username);
  }
}
