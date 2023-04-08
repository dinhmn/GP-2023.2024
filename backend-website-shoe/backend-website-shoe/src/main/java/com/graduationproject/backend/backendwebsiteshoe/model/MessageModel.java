package com.graduationproject.backend.backendwebsiteshoe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Message model.
 *
 * @author Mai Ngoc Dinh.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageModel {

  private Long chatId;

  private Long userId;

  private String messageFrom;

  private String messageTo;

  private String messageText;

  private String messageTime;
}
