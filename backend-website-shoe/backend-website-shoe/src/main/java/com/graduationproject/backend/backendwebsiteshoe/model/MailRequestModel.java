package com.graduationproject.backend.backendwebsiteshoe.model;

import lombok.Data;

/**
 * Mail request dto.
 *
 * @author Mai Ngoc Dinh.
 */
@Data
public class MailRequestModel {

  private String mailName;

  private String mailTo;

  private String mailFrom;

  private String mailSubject;
}
