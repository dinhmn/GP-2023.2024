package com.graduationproject.backend.backendwebsiteshoe.repository;

import com.graduationproject.backend.backendwebsiteshoe.dto.IContact;
import com.graduationproject.backend.backendwebsiteshoe.entity.ContactEntity;
import com.graduationproject.backend.backendwebsiteshoe.entity.ContactEntityKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<ContactEntity, ContactEntityKey> {

    /**
     * Get all comment of product.
     *
     * @return list of comment.
     */
    @Query(value = "SELECT contact.contact_id AS contactId, contact.contact_name AS contactName, "
            + " contact.contact_email AS contactEmail, contact.contact_message AS contactMessage,"
            + " product.product_id AS productId, product.product_name AS productName"
            + " contact.create_date AS createDate, contact.update_date AS updateDate, contact.status AS status"
            + " FROM tbl_contact contact"
            + " LEFT JOIN tbl_product product ON product.product_id = contact.product_id", nativeQuery = true)
    List<IContact> getAllContact();
}
