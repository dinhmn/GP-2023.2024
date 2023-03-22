package com.graduationproject.backend.backendwebsiteshoe.entity;

import jakarta.persistence.Column;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserInformationEntityKey implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "user_information_id", nullable = false)
    private Long userInformationId;

    @Column(name = "source_image_id", nullable = false)
    private Long sourceImageId;

    public UserInformationEntityKey setPk(Long sourceImageId, Long userInformationId) {
        setSourceImageId(sourceImageId);
        setUserInformationId(userInformationId);
        return this;
    }
}
