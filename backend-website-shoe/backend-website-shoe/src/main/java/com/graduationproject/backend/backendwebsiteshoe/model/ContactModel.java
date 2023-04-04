package com.graduationproject.backend.backendwebsiteshoe.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model contact.
 *
 * @author Mai Ngoc Dinh.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactModel {

  private Long productId;

  private String contactEmail;

  private String contactMessage;

  private String contactName;

  private String contactStatus;

}
