package com.graduationproject.backend.backendwebsiteshoe.repository;

import com.graduationproject.backend.backendwebsiteshoe.entity.TrademarkEntity;
import com.graduationproject.backend.backendwebsiteshoe.entity.TrademarkEntityKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Implement repository of trademark.
 *
 * @author Mai Ngoc Dinh.
 */
@Repository
public interface TrademarkRepository extends JpaRepository<TrademarkEntity, TrademarkEntityKey> {
}
