package com.graduationproject.backend.backendwebsiteshoe.dto;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Data transfer object userDTO.
 *
 * @author Mai Ngoc Dinh
 */
public interface UserRoleDTO {

  Long getUserId();

  Long getRoleId();

  String getRoleName();

  String getUsername();

  String getUserPassword();

  String getUserEmail();

  String getFirstName();

  String getLastName();

  @DateTimeFormat(fallbackPatterns = "yyyy/MM/dd HH:mm:ss")
  Date getCreatedDate();

  Boolean getStatus();
}
