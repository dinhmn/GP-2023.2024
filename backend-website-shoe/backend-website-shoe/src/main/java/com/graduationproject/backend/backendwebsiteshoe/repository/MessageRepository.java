package com.graduationproject.backend.backendwebsiteshoe.repository;

import com.graduationproject.backend.backendwebsiteshoe.entity.MessageEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Implement repository of message.
 *
 * @author Mai Ngoc Dinh
 */
@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {

  /**
   * Find by chatId.
   *
   * @param messageFrom messageFrom
   * @param messageTo   messageTo
   * @param chatId      chatId
   * @return entity
   */
  @Query(value = "SELECT * FROM tbl_message message "
      + " WHERE ((message.message_from = ?1 and message.message_to = ?2)"
      + " OR (message.message_from = ?2 and message.message_to = ?1))"
      + " AND IF(?3 IS NOT NULL, message.chat_id = ?3, TRUE)", nativeQuery = true)
  List<MessageEntity> findAll(String messageFrom, String messageTo, Long chatId);

  /**
   * Find by chatId.
   *
   * @param chatId chatId
   * @return entity
   */
  @Query(value = "SELECT * FROM tbl_message message "
      + " WHERE message.chat_id = ?1", nativeQuery = true)
  List<MessageEntity> findAll(Long chatId);
}
