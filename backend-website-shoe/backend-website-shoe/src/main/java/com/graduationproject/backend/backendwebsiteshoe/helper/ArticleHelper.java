package com.graduationproject.backend.backendwebsiteshoe.helper;

import com.graduationproject.backend.backendwebsiteshoe.common.CommonService;
import com.graduationproject.backend.backendwebsiteshoe.common.Constant;
import com.graduationproject.backend.backendwebsiteshoe.common.Image;
import com.graduationproject.backend.backendwebsiteshoe.common.User;
import com.graduationproject.backend.backendwebsiteshoe.dto.IArticle;
import com.graduationproject.backend.backendwebsiteshoe.dto.IArticleDTO;
import com.graduationproject.backend.backendwebsiteshoe.entity.ArticleEntity;
import com.graduationproject.backend.backendwebsiteshoe.entity.ProductEntity;
import com.graduationproject.backend.backendwebsiteshoe.forms.ArticleForm;
import com.graduationproject.backend.backendwebsiteshoe.forms.ArticleFormPage;
import com.graduationproject.backend.backendwebsiteshoe.model.ArticleModel;
import com.graduationproject.backend.backendwebsiteshoe.service.ArticleService;
import com.graduationproject.backend.backendwebsiteshoe.service.SourceImageService;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * Implement helper of article.
 *
 * @author Mai Ngoc Dinh
 */
@Component
public class ArticleHelper {

  @Autowired
  CommonService commonService;

  @Autowired
  ArticleService articleService;

  @Autowired
  SourceImageService sourceImageService;

  /**
   * Select all article.
   *
   * @param pageNo pageNo
   * @param pageSize pageSize
   * @param sortBy sortBy
   * @param sortDirection sortDirection
   * @return list article.
   */
  @NonNull
  public ArticleFormPage getAllArticle(int pageNo, int pageSize, String sortBy,
                                       String sortDirection) {
    Pageable pageable = commonService.setPageable(pageSize, pageNo, sortBy, sortDirection);

    // Create pageable instance
    Page<ArticleEntity> article = articleService.getAll(pageable);

    // Get content for page object
    List<ArticleEntity> articleList = article.getContent();

    return ArticleFormPage.builder()
        .articleEntityList(articleList)
        .pageNo(article.getNumber())
        .pageSize(article.getSize())
        .totalElements(article.getTotalElements())
        .totalPages(article.getTotalPages())
        .last(article.isLast())
        .build();
  }

  /**
   * Select all article.
   *
   * @param pageNo pageNo
   * @param pageSize pageSize
   * @param sortBy sortBy
   * @param sortDirection sortDirection
   * @return list article.
   */
  @NonNull
  public ArticleFormPage getAllArticleUser(int pageNo, int pageSize, String sortBy,
                                             String sortDirection) {

    Pageable pageable = commonService.setPageable(pageSize, pageNo, sortBy, sortDirection);

    // Create pageable instance
    Page<IArticleDTO> article = articleService.getAllArticle(pageable);

    // Get content for page object
    List<IArticleDTO> articleList = article.getContent();

    return ArticleFormPage.builder()
        .articleDTOList(articleList)
        .pageNo(article.getNumber())
        .pageSize(article.getSize())
        .totalElements(article.getTotalElements())
        .totalPages(article.getTotalPages())
        .last(article.isLast())
        .build();
  }

  /**
   * Select article by articleId.
   *
   * @param articleId articleId
   * @return article.
   */
  @NonNull
  public Optional<IArticle> getArticleByKey(Long articleId) {
    return articleService
        .getArticleByKey(articleId, User.CUSTOMER.getCode(), Image.IMAGE_ARTICLE.getCode());
  }

  /**
   * Insert new entity of article.
   *
   * @param articleForm articleForm
   * @return entity category.
   */
  public ArticleEntity insert(@NonNull ArticleForm articleForm) throws IOException {
    ArticleEntity articleEntity =
        this.toBuildArticle(articleForm.getArticleModel(), Constant.INSERT);
    ArticleEntity entity = articleService.insert(articleEntity);

    sourceImageService
        .insertOrUpdate(articleForm.getFile(), entity.getArticleId(), Constant.INSERT);
    return entity;
  }

  /**
   * Insert new entity of article.
   *
   * @param articleForm articleForm
   * @return entity category.
   */
  public ArticleEntity update(@NonNull ArticleForm articleForm) throws IOException {
    ArticleEntity articleEntity =
        this.toBuildArticle(articleForm.getArticleModel(), Constant.UPDATE);
    ArticleEntity entity = articleService.update(articleEntity, articleEntity.getArticleId());

    sourceImageService
        .insertOrUpdate(articleForm.getFile(), entity.getArticleId(), Constant.UPDATE);
    return entity;
  }

  /**
   * Delete all article.
   *
   * @param articleId articleId
   * @return TRUE or FALSE
   */
  public Boolean delete(Long articleId) {
    // Delete article
    Optional<ArticleEntity> articleEntityOptional = articleService.getArticleByArticle(articleId);

    // Delete source image
    if (articleEntityOptional.isPresent()) {
      articleService.delete(articleId);
      sourceImageService.deleteByArticleId(articleId);
      return Boolean.TRUE;
    }

    return Boolean.FALSE;
  }

  /**
   * Build article entity.
   *
   * @param articleModel articleModel
   * @param type         type
   * @return entity.
   */
  private ArticleEntity toBuildArticle(ArticleModel articleModel, String type) {
    ArticleEntity articleEntity = new ArticleEntity();
    if (Constant.UPDATE.equals(type)) {
      articleEntity.setArticleId(articleModel.getArticleId());
    }
    articleEntity.setArticleName(articleModel.getArticleName());
    articleEntity.setArticleDescription(articleModel.getArticleDescription());
    articleEntity.setArticleSeo(commonService.setSeo(articleEntity.getArticleName()));
    articleEntity.setProductId(articleModel.getProductId());
    commonService.setCommonCreatedEntity(articleEntity);
    articleEntity.setStatus(
        articleModel.getArticleStatus().equals(Constant.TRUE) ? Boolean.TRUE : Boolean.FALSE);
    return articleEntity;
  }
}
