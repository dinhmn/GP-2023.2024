package com.graduationproject.backend.backendwebsiteshoe.service;

import com.graduationproject.backend.backendwebsiteshoe.dto.IArticle;
import com.graduationproject.backend.backendwebsiteshoe.dto.IArticleDTO;
import com.graduationproject.backend.backendwebsiteshoe.entity.ArticleEntity;
import com.graduationproject.backend.backendwebsiteshoe.repository.ArticleRepository;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

/**
 * Implement service of article.
 *
 * @author Mai Ngoc Dinh
 */
@Service
public class ArticleService {

  @Autowired
  ArticleRepository articleRepository;

  /**
   * Get all category.
   *
   * @param pageable pageable
   * @return list of entity category.
   */
  public Page<ArticleEntity> getAll(Pageable pageable) {
    return articleRepository.findAll(pageable);
  }

  /**
   * Get all article.
   *
   * @param pageable pageable
   * @return list of entity article.
   */
  public Page<IArticleDTO> getAllArticle(Pageable pageable) {
    return articleRepository.findAllByPageable(pageable);
  }

  /**
   * Get one article.
   *
   * @param articleId articleId
   * @return article.
   */
  public Optional<ArticleEntity> getArticleByArticle(Long articleId) {
    return articleRepository.findById(articleId);
  }

  /**
   * Get one article.
   *
   * @param articleId   articleId
   * @param userCode    userCode
   * @param commentCode commentCode
   * @return article.
   */
  public Optional<IArticle> getArticleByKey(Long articleId, String userCode, String commentCode) {
    return articleRepository.findByArticleId(articleId, userCode, commentCode);
  }

  /**
   * Insert new entity of article.
   *
   * @param articleEntity articleEntity
   * @return entity category.
   */
  public ArticleEntity insert(@NonNull ArticleEntity articleEntity) {
    return articleRepository.save(articleEntity);
  }

  /**
   * Update article by primary key.
   *
   * @param articleEntity articleEntity
   * @param articleId     articleId
   * @return entity category.
   */
  public ArticleEntity update(@NonNull ArticleEntity articleEntity, @NonNull Long articleId) {
    Optional<ArticleEntity> articleEntityOptional = articleRepository.findById(articleId);

    if (articleEntityOptional.isPresent()) {
      this.toBuildCategory(articleEntity, articleEntityOptional.get());
      return articleRepository.save(articleEntityOptional.get());
    }

    return articleRepository.save(articleEntity);
  }

  /**
   * Delete article by primary key.
   *
   * @param articleId articleId
   */
  public void delete(@NonNull Long articleId) {
    articleRepository.deleteById(articleId);
  }

  /**
   * To build article.
   *
   * @param currentEntity  entityInInput
   * @param databaseEntity entityInDatabase
   */
  private void toBuildCategory(ArticleEntity currentEntity, ArticleEntity databaseEntity) {
    databaseEntity.setProductId(currentEntity.getProductId());
    databaseEntity.setArticleDescription(currentEntity.getArticleDescription());
    databaseEntity.setArticleName(currentEntity.getArticleName());
    databaseEntity.setArticleSeo(currentEntity.getArticleSeo());
    databaseEntity.setUpdatedDate(new Date());
    databaseEntity.setUpdatedBy(1);
    databaseEntity.setStatus(Boolean.TRUE);
  }

}
