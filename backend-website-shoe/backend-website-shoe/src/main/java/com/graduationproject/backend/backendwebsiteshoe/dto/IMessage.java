package com.graduationproject.backend.backendwebsiteshoe.dto;

import java.util.Date;

public interface IMessage {

  Long getChatId();
  String getMessageText();
  Long getUserId();
  String getMessageFrom();
  String getMessageTo();
  Date getCreatedDate();
}
