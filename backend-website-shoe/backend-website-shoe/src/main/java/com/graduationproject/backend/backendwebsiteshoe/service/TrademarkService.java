package com.graduationproject.backend.backendwebsiteshoe.service;

import com.graduationproject.backend.backendwebsiteshoe.entity.CategoryEntity;
import com.graduationproject.backend.backendwebsiteshoe.entity.TrademarkEntity;
import com.graduationproject.backend.backendwebsiteshoe.repository.TrademarkRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implement service of trademark.
 *
 * @author Mai Ngoc Dinh
 */
@Service
public class TrademarkService {

  @Autowired
  TrademarkRepository trademarkRepository;

  /**
   * Get all trademark.
   *
   * @return list of entity category.
   */
  public List<TrademarkEntity> getAll() {
    return trademarkRepository.findAll();
  }
}
