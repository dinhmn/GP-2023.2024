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
 * User information entity.
 *
 * @author Mai Ngoc Dinh.
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "tbl_user_information")
public class UserInformationEntity extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  public UserInformationEntity() {

    super();
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_information_id", nullable = false)
  private Long userInformationId;

  @Column(name = "source_image_id")
  private Long sourceImageId;

  @Column(name = "user_id")
  private Long userId;

  /*
   * UserCode = 10 => User
   * UserCode = 11 => Customer Not User
   * */
  @Column(name = "user_code", length = 2, nullable = false)
  private String userCode;

  @Column(name = "first_name", length = 40)
  private String firstName;

  @Column(name = "lastName", length = 40)
  private String lastName;

  @Column(name = "email", length = 80, nullable = false)
  private String email;

  @Column(name = "phone", length = 10, nullable = false)
  private String phone;

  @Column(name = "address", nullable = false)
  private String address;

  @Column(name = "note")
  private String note;

  @Column(name = "age", length = 2)
  private String age;

}
