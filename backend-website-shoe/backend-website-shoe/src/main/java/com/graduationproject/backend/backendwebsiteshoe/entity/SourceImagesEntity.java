package com.graduationproject.backend.backendwebsiteshoe.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@IdClass(SourceImagesEntityKey.class)
public class SourceImagesEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    public SourceImagesEntity() {

        super();
    }

    public SourceImagesEntity(String fileName, String fileType, byte[] data) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
    }

    @Id
    @Column(name = "image_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private Long imageId;

    @Id
    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "user_information_id")
    private Long userInformationId;

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Column(name = "file_type", nullable = false)
    private String fileType;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "data", nullable = false)
    private byte[] data;

    /*
    * ImageCode = 30 => Image user
    * ImageCode = 31 => Image main product
    * ImageCode = 32 => Image multiple product
    * */
    @Column(name = "image_code", length = 2)
    private String imageCode;
}
