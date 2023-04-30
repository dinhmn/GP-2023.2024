package com.graduationproject.backend.backendwebsiteshoe.controller;

import com.graduationproject.backend.backendwebsiteshoe.common.CommonService;
import com.graduationproject.backend.backendwebsiteshoe.common.Constant;
import com.graduationproject.backend.backendwebsiteshoe.dto.UserRoleDTO;
import com.graduationproject.backend.backendwebsiteshoe.entity.RoleEntity;
import com.graduationproject.backend.backendwebsiteshoe.entity.UserEntity;
import com.graduationproject.backend.backendwebsiteshoe.forms.UserForm;
import com.graduationproject.backend.backendwebsiteshoe.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

  @Autowired
  CommonService commonService;

  /**
   * Get all information.
   *
   * @return response list entity.
   */
  @GetMapping("/get-all")
  public UserForm getAll(
      @RequestParam(value = "page_no", defaultValue = Constant.DEFAULT_PAGE_NUMBER) int pageNo,
      @RequestParam(value = "page_size", defaultValue = Constant.DEFAULT_PAGE_SIZE) int pageSize,
      @RequestParam(value = "sort_direction", defaultValue = Constant.DEFAULT_SORT_DIRECTION)
          String sortDirection,
      @RequestParam(value = "sort_by", defaultValue = "userId") String sortBy) {
    return this.getAllUser(pageNo, pageSize, sortBy, sortDirection);
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

  /**
   * Select all user.
   *
   * @param pageNo pageNo
   * @param pageSize pageSize
   * @param sortBy sortBy
   * @param sortDirection sortDirection
   * @return list article.
   */
  private UserForm getAllUser(int pageNo, int pageSize, String sortBy,
                          String sortDirection) {
    Pageable pageable = commonService.setPageable(pageSize, pageNo, sortBy, sortDirection);

    Page<UserRoleDTO> userPage = userService.getAll(pageable);

    List<RoleEntity> roleEntityList = userService.getAllRole();

    return UserForm.builder()
        .userEntityList(userPage.getContent().stream().distinct().toList())
        .roleEntityList(roleEntityList)
        .pageNo(userPage.getNumber())
        .pageSize(userPage.getSize())
        .totalElements(userPage.getTotalElements())
        .totalPages(userPage.getTotalPages())
        .last(userPage.isLast())
        .build();
  }
}
