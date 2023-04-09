package com.graduationproject.backend.backendwebsiteshoe.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
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

  @Column(name = "product_id")
  private Long productId;

  @Column(name = "article_id")
  private Long articleId;

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
   * ImageCode = 34 => Image article
   * */
  @Column(name = "image_code", length = 2)
  private String imageCode;
}
