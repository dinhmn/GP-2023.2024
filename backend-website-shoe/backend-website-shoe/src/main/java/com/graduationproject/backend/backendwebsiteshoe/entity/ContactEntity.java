package com.graduationproject.backend.backendwebsiteshoe.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Contact entity.
 *
 * @author Mai Ngoc Dinh.
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@IdClass(ContactEntityKey.class)
@Table(name = "tbl_contact")
public class ContactEntity extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  public ContactEntity() {

    super();
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "contact_id", nullable = false)
  private Long contactId;

  @Column(name = "product_id", nullable = false)
  private Long productId;

  @Column(name = "contact_name", nullable = false)
  private String contactName;

  @Column(name = "contact_email", nullable = false)
  private String contactEmail;

  @Column(name = "contact_message", length = 1000)
  private String contactMessage;
}
