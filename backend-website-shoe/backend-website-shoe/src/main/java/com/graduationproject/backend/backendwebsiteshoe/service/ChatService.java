package com.graduationproject.backend.backendwebsiteshoe.service;

import com.graduationproject.backend.backendwebsiteshoe.entity.ChatEntity;
import com.graduationproject.backend.backendwebsiteshoe.repository.ChatRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implement service of chat.
 *
 * @author Mai Ngoc Dinh
 */
@Service
public class ChatService {

  @Autowired
  ChatRepository chatRepository;

  /**
   * Insert new chat.
   *
   * @param chatEntity chatEntity
   * @return list entity
   */
  public ChatEntity insert(ChatEntity chatEntity) {
    return chatRepository.save(chatEntity);
  }

  /**
   * Select by chatId.
   *
   * @param chatId chatId
   * @return entity
   */
  public Optional<ChatEntity> getById(Long chatId) {
    return chatRepository.findById(chatId);
  }

  /**
   * Select all.
   *
   * @return list of entity.
   */
  public List<ChatEntity> getAll() {
    return chatRepository.findAll();
  }

}
