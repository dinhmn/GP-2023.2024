package com.graduationproject.backend.backendwebsiteshoe.repository;

import com.graduationproject.backend.backendwebsiteshoe.dto.IComment;
import com.graduationproject.backend.backendwebsiteshoe.entity.CommentEntity;
import com.graduationproject.backend.backendwebsiteshoe.entity.CommentEntityKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, CommentEntityKey> {

    /**
     * Get all comment of product.
     *
     * @param productId productId
     * @return list of comment.
     */
    @Query(value = "SELECT comment.comment_id AS commentId, info.first_name AS firstName, "
            + " info.last_name AS lastName, comment.comment_detail AS commentDetail, rate.star_rating AS rate"
            + " FROM tbl_product product"
            + " INNER JOIN tbl_comment comment ON product.product_id = comment.product_id"
            + " INNER JOIN tbl_user user ON user.user_id = comment.user_id"
            + " INNER JOIN tbl_user_information info ON info.user_id = user.user_id AND info.user_information_id = user.profile_id"
            + " INNER JOIN tbl_rates rate ON rate.product_id = product.product_id AND comment.comment_id = rate.comment_id"
            + " WHERE product.product_id = ?1", nativeQuery = true)
    List<IComment> findAllCommentByProductId(String productId);
}
