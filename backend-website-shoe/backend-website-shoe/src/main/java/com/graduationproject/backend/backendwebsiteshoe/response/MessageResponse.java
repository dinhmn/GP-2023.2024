package com.graduationproject.backend.backendwebsiteshoe.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Message response from user.
 *
 * @author Mai Ngoc Dinh.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageResponse {

  private String messageTo;

  private String messageFrom;

  private String messageText;

  private String messageTime;
}
