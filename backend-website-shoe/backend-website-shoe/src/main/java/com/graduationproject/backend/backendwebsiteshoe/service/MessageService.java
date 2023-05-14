package com.graduationproject.backend.backendwebsiteshoe.service;

import com.graduationproject.backend.backendwebsiteshoe.dto.IMessage;
import com.graduationproject.backend.backendwebsiteshoe.entity.MessageEntity;
import com.graduationproject.backend.backendwebsiteshoe.repository.MessageRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implement service of message.
 *
 * @author Mai Ngoc Dinh
 */
@Service
public class MessageService {

  @Autowired
  MessageRepository messageRepository;

  /**
   * Insert message into database.
   *
   * @param messageEntity messageEntity
   * @return entity
   */
  public MessageEntity insert(MessageEntity messageEntity) {
    return messageRepository.save(messageEntity);
  }

  /**
   * Select all by messageTo and messageFrom.
   *
   * @return list entity
   */
  public List<IMessage> selectAll() {
    return messageRepository.findAllApi();
  }

  /**
   * Select all by chatId.
   *
   * @param userId userId
   * @return list entity
   */
  public List<MessageEntity> selectByChatId(Long userId) {
    return messageRepository.findAll(userId);
  }

  /**
   * Select all by messageTo, messageFrom and chatId.
   *
   * @param messageFrom messageFrom
   * @param messageTo   messageTo
   * @param userId      userId
   * @return list entity
   */
  public List<MessageEntity> selectByMessageFromByMessageToByChatId(String messageFrom,
                                                                    String messageTo, Long userId) {
    return messageRepository.findAll(messageFrom, messageTo, userId);
  }
}
