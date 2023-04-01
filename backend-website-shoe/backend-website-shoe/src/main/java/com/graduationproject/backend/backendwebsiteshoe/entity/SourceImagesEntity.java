package com.graduationproject.backend.backendwebsiteshoe.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

/**
 * Source images entity.
 *
 * @author Mai Ngoc Dinh.
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@IdClass(SourceImagesEntityKey.class)
@Table(name = "tbl_source_images")
public class SourceImagesEntity extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  public SourceImagesEntity() {

    super();
  }

  /**
   * Set entity images.
   *
   * @param fileName fileName
   * @param fileType fileType
   * @param data     data
   */
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
   * ImageCode = 33 => Image product color
   * */
  @Column(name = "image_code", length = 2)
  private String imageCode;
}
