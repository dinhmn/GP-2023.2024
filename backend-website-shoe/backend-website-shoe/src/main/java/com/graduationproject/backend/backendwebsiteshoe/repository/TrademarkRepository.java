package com.graduationproject.backend.backendwebsiteshoe.repository;

import com.graduationproject.backend.backendwebsiteshoe.dto.IProduct;
import com.graduationproject.backend.backendwebsiteshoe.entity.CategoryEntity;
import com.graduationproject.backend.backendwebsiteshoe.entity.CategoryEntityKey;
import com.graduationproject.backend.backendwebsiteshoe.entity.TrademarkEntity;
import com.graduationproject.backend.backendwebsiteshoe.entity.TrademarkEntityKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrademarkRepository extends JpaRepository<TrademarkEntity, TrademarkEntityKey> {
}
