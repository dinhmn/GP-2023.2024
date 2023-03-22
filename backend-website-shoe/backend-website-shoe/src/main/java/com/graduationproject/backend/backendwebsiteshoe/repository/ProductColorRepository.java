package com.graduationproject.backend.backendwebsiteshoe.repository;

import com.graduationproject.backend.backendwebsiteshoe.entity.ProductColorEntity;
import com.graduationproject.backend.backendwebsiteshoe.entity.ProductColorEntityKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductColorRepository extends JpaRepository<ProductColorEntity, ProductColorEntityKey> {
}
