package com.graduationproject.backend.backendwebsiteshoe.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Message entity.
 *
 * @author Mai Ngoc Dinh.
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "tbl_message")
public class MessageEntity extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  public MessageEntity() {

    super();
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "message_id", nullable = false)
  private Long messageId;

  @Column(name = "chat_id", nullable = false)
  private Long chatId;

  @Column(name = "message_text", length = 3000)
  private String messageText;

  @Column(name = "message_from")
  private String messageFrom;

  @Column(name = "message_to")
  private String messageTo;
}
