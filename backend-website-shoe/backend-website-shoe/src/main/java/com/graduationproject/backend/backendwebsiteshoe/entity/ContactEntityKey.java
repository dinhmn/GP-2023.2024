package com.graduationproject.backend.backendwebsiteshoe.entity;

import jakarta.persistence.Column;
import java.io.Serializable;
import lombok.Data;

/**
 * Contact entity key.
 *
 * @author Mai Ngoc Dinh.
 */
@Data
public class ContactEntityKey implements Serializable {

  private static final long serialVersionUID = 1L;

  @Column(name = "contact_id", nullable = false)
  private Long contactId;

  /**
   * Set PK.
   *
   * @param contactId contactId
   * @return key.
   */
  public ContactEntityKey setPk(Long contactId) {
    setContactId(contactId);
    return this;
  }
}
