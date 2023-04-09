package com.graduationproject.backend.backendwebsiteshoe.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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
