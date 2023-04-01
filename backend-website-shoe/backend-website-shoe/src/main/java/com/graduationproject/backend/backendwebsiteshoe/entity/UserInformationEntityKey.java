package com.graduationproject.backend.backendwebsiteshoe.entity;

import jakarta.persistence.Column;
import java.io.Serializable;
import lombok.Data;

/**
 * User information entity key.
 *
 * @author Mai Ngoc Dinh.
 */
@Data
public class UserInformationEntityKey implements Serializable {

  private static final long serialVersionUID = 1L;

  @Column(name = "user_information_id", nullable = false)
  private Long userInformationId;

  @Column(name = "source_image_id", nullable = false)
  private Long sourceImageId;

  /**
   * Set PK.
   *
   * @param sourceImageId     sourceImageId
   * @param userInformationId userInformationId
   * @return key.
   */
  public UserInformationEntityKey setPk(Long sourceImageId, Long userInformationId) {
    setSourceImageId(sourceImageId);
    setUserInformationId(userInformationId);
    return this;
  }
}
