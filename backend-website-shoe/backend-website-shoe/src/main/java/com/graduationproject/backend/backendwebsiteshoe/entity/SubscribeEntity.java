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
 * Subscribe entity.
 *
 * @author Mai Ngoc Dinh.
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "tbl_subscribe")
public class SubscribeEntity extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  public SubscribeEntity() {

    super();
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "subscribe_id", nullable = false)
  private Long subscribeId;

  @Column(name = "subscribe_email", nullable = false)
  private String subscribeEmail;
}
