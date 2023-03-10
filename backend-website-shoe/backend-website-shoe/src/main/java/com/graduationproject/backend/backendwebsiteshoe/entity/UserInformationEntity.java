package com.graduationproject.backend.backendwebsiteshoe.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@IdClass(UserEntityKey.class)
public class UserInformationEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    public UserInformationEntity() {

        super();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_information_id", nullable = false)
    private Long userInformationId;

    @Id
    @Column(name = "source_image_id", nullable = false)
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
    private Long lastName;

    @Column(name = "email", length = 80, nullable = false)
    private String email;

    @Column(name = "phone", length = 10, nullable = false)
    private String phone;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "age", length = 2)
    private String age;

}
