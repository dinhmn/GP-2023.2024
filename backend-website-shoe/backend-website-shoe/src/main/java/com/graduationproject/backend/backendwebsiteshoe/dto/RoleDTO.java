package com.graduationproject.backend.backendwebsiteshoe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data transfer object roleDTO.
 *
 * @author Mai Ngoc Dinh
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class RoleDTO {

  private Long roleId;

  private String roleName;
}
