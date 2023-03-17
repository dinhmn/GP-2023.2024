package com.graduationproject.backend.backendwebsiteshoe.repository;

import com.graduationproject.backend.backendwebsiteshoe.entity.ProductSizeEntity;
import com.graduationproject.backend.backendwebsiteshoe.entity.ProductSizeEntityKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSizeRepository extends JpaRepository<ProductSizeEntity, ProductSizeEntityKey> {
}
