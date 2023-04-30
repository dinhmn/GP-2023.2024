package com.graduationproject.backend.backendwebsiteshoe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data transfer object userDTO.
 *
 * @author Mai Ngoc Dinh
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class UserDTO {

  private Long userId;

  private String username;

  private String password;

  private String email;
}
