package com.graduationproject.backend.backendwebsiteshoe.controller;

import com.graduationproject.backend.backendwebsiteshoe.entity.RoleEntity;
import com.graduationproject.backend.backendwebsiteshoe.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Implement user service.
 *
 * @author Mai Ngoc Dinh
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/role")
public class RoleController {

  @Autowired
  UserService userService;

  @GetMapping("/get")
  public List<RoleEntity> getUserById() {
    return userService.getAllRole();
  }

}
