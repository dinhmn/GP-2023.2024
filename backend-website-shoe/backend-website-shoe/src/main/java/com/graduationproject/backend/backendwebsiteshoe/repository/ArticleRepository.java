package com.graduationproject.backend.backendwebsiteshoe.repository;

import com.graduationproject.backend.backendwebsiteshoe.dto.IArticle;
import com.graduationproject.backend.backendwebsiteshoe.entity.ArticleEntity;
import com.graduationproject.backend.backendwebsiteshoe.entity.ArticleEntityKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, ArticleEntityKey> {

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
            + " info.first_name AS commentFirstName, info.last_name AS commentLastName, comment.comment_detail AS commentTitle"
            + " FROM tbl_article article"
            + " INNER JOIN tbl_comment comment ON article.article_id = comment.article_id"
            + " INNER JOIN tbl_user user ON user.user_id = comment.user_id"
            + " INNER JOIN tbl_user_information info ON info.user_id = user.user_id AND info.user_information_id = user.profile_id"
            + " WHERE article.article_id = ?1 AND info.user_code = ?2 AND comment.comment_code = ?3 ", nativeQuery = true)
    Optional<IArticle> findAllByArticleId(Long articleId, String userCode, String commentCode);
}
