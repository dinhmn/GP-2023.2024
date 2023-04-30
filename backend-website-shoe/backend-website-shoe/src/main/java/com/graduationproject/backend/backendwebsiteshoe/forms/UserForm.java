package com.graduationproject.backend.backendwebsiteshoe.forms;

import com.graduationproject.backend.backendwebsiteshoe.dto.UserRoleDTO;
import com.graduationproject.backend.backendwebsiteshoe.entity.RoleEntity;
import com.graduationproject.backend.backendwebsiteshoe.entity.UserEntity;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Form response pageable.
 *
 * @author Mai Ngoc Dinh
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserForm {

  private List<UserRoleDTO> userEntityList;

  private List<RoleEntity> roleEntityList;

  private int pageNo;

  private int pageSize;

  private long totalElements;

  private int totalPages;

  private boolean last;
}
