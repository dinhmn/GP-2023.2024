package com.graduationproject.backend.backendwebsiteshoe.repository;

import com.graduationproject.backend.backendwebsiteshoe.dto.IArticle;
import com.graduationproject.backend.backendwebsiteshoe.dto.IArticleDTO;
import com.graduationproject.backend.backendwebsiteshoe.entity.ArticleEntity;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Implement repository of article.
 *
 * @author Mai Ngoc Dinh
 */
@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {

  @Query(value = "SELECT * FROM tbl_article art WHERE art.article_name LIKE %?1%", nativeQuery = true,
  countQuery = "SELECT COUNT(*) FROM tbl_article art WHERE art.article_name LIKE %?1%")
  Page<ArticleEntity> findAll(String searchValue, Pageable pageable);

  /**
   * Get one article.
   *
   * @param articleId   articleId
   * @param userCode    userCode
   * @param commentCode commentCode
   * @return article.
   */
  @Query(value = "SELECT article.article_id AS articleId, article.article_name AS articleName, "
      + " article.article_description AS articleDescription, info.email AS commentEmail, "
      + " info.first_name AS commentFirstName, info.last_name AS commentLastName,"
      + " comment.comment_detail AS commentTitle"
      + " FROM tbl_article article"
      + " INNER JOIN tbl_comment comment ON article.article_id = comment.article_id"
      + " INNER JOIN tbl_user usr ON usr.user_id = comment.user_id"
      + " INNER JOIN tbl_user_information info ON info.user_id = usr.user_id "
      + " AND info.user_information_id = usr.profile_id"
      + " WHERE article.article_id = ?1 AND info.user_code = ?2 AND comment.comment_code = ?3 ",
      nativeQuery = true)
  Optional<IArticle> findByArticleId(Long articleId, String userCode, String commentCode);

  /**
   * Get article.
   *
   * @param articleId articleId
   * @return article.
   */
  @Query(value = "SELECT article.article_id AS articleId, article.article_name AS articleName, "
      + " article.article_description AS articleDescription, product.product_id AS productId, "
      + " article.article_seo AS articleSeo, article.created_date AS createdDate, "
      + " images.file_name AS fileName, images.file_type AS fileType, images.data AS fileData, "
      + " product.product_name AS productName, article.updated_date AS updatedDate, "
      + " article.status AS articleStatus"
      + " FROM tbl_article article"
      + " LEFT JOIN tbl_product product ON product.product_id = article.product_id"
      + " LEFT JOIN tbl_source_images images ON images.article_id = article.article_id"
      + " WHERE article.article_id = ?1", nativeQuery = true)
//  AND images.image_code = 34
  Optional<IArticleDTO> findByArticleId(Long articleId);

  /**
   * Get all article.
   *
   * @param pageable pageable
   * @return article.
   */
  @Query(value = "SELECT article.article_id AS articleId, article.article_name AS articleName, "
      + " article.article_description AS articleDescription, product.product_id AS productId, "
      + " article.article_seo AS articleSeo, article.created_date AS createdDate, "
      + " images.file_name AS fileName, images.file_type AS fileType, images.data AS fileData, "
      + " product.product_name AS productName, article.updated_date AS updatedDate, "
      + " article.status AS articleStatus"
      + " FROM tbl_article article"
      + " LEFT JOIN tbl_product product ON product.product_id = article.product_id"
      + " LEFT JOIN tbl_source_images images ON images.article_id = article.article_id"
      + " AND images.image_code = 34", nativeQuery = true, countQuery = "SELECT COUNT(*)"
      + " FROM tbl_article article"
      + " LEFT JOIN tbl_product product ON product.product_id = article.product_id"
      + " LEFT JOIN tbl_source_images images ON images.article_id = article.article_id"
      + " AND images.image_code = 34")
  Page<IArticleDTO> findAllByPageable(Pageable pageable);
}
