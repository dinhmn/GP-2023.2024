package com.graduationproject.backend.backendwebsiteshoe.repository;

import com.graduationproject.backend.backendwebsiteshoe.dto.IMessage;
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
   * @param userId      userId
   * @return entity
   */
  @Query(value = "SELECT * FROM tbl_message message INNER JOIN tbl_chat  chat ON message.chat_id = chat.chat_id"
      + " WHERE ((message.message_from = ?1 and message.message_to = ?2)"
      + " OR (message.message_from = ?2 and message.message_to = ?1))"
      + " AND IF(?3 IS NOT NULL, chat.user_id = ?3, TRUE)", nativeQuery = true)
  List<MessageEntity> findAll(String messageFrom, String messageTo, Long userId);

  /**
   * Find by chatId.
   *
   * @param userId userId
   * @return entity
   */
  @Query(value = "SELECT * FROM tbl_message message INNER JOIN tbl_chat  chat ON message.chat_id = chat.chat_id"
      + " WHERE message.user_id = ?1", nativeQuery = true)
  List<MessageEntity> findAll(Long userId);

  /**
   * Find by chatId.
   *
   * @return entity
   */
  @Query(value = "SELECT chat.chat_id AS chatId, message.message_text AS messageText,"
      + " chat.user_id AS userId, message.message_from AS messageFrom, message.message_to AS messageTo,"
      + " message.created_date AS createdDate FROM tbl_message message "
      + " INNER JOIN tbl_chat  chat ON message.chat_id = chat.chat_id ORDER BY message.created_date", nativeQuery = true)
  List<IMessage> findAllApi();


}
