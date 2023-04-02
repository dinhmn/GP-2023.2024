package com.graduationproject.backend.backendwebsiteshoe.repository;

import com.graduationproject.backend.backendwebsiteshoe.dto.IContact;
import com.graduationproject.backend.backendwebsiteshoe.entity.ContactEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Implement repository of contact.
 *
 * @author Mai Ngoc Dinh.
 */
@Repository
public interface ContactRepository extends JpaRepository<ContactEntity, Long> {

  /**
   * Get all comment of product.
   *
   * @return list of comment.
   */
  @Query(value = "SELECT contact.contact_id AS contactId,"
      + "  contact.contact_name AS contactName, "
      + " contact.contact_email AS contactEmail, "
      + " contact.contact_message AS contactMessage,"
      + " product.product_id AS productId, "
      + " product.product_name AS productName"
      + " contact.create_date AS createDate, "
      + " contact.update_date AS updateDate, contact.status AS status"
      + " FROM tbl_contact contact"
      + " LEFT JOIN tbl_product product ON product.product_id = contact.product_id"
      + " WHERE contact.contact_email LIKE %?1% OR contact.contact_name LIKE %?1% ",
      nativeQuery = true, countQuery = "SELECT COUNT(*)"
      + " FROM tbl_contact contact"
      + " LEFT JOIN tbl_product product ON product.product_id = contact.product_id"
      + " WHERE contact.contact_email LIKE %?1% OR contact.contact_name LIKE %?1% ")
  Page<IContact> getAllContact(String searchValue, Pageable pageable);

}
