package com.graduationproject.backend.backendwebsiteshoe.controller;

import com.graduationproject.backend.backendwebsiteshoe.entity.UserEntity;
import com.graduationproject.backend.backendwebsiteshoe.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Implement controller of user.
 *
 * @author Mai Ngoc Dinh.
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/user")
public class UserController {

  @Autowired
  UserService userService;

  /**
   * Get all information.
   *
   * @return response list entity.
   */
  @GetMapping("/get-all")
  public List<UserEntity> getAll() {
    return userService.getAll();
  }

  /**
   * Login with information from UI.
   *
   * @param userId userId
   * @param roleId roleId
   * @return entity.
   */
  @PutMapping("/update-role/{userId}/{roleId}")
  public UserEntity updateRole(@PathVariable Long userId, @PathVariable Long roleId) {
    return userService.changeRoleName(userId, roleId);
  }
}
