package com.graduationproject.backend.backendwebsiteshoe.controller;

import com.graduationproject.backend.backendwebsiteshoe.entity.TrademarkEntity;
import com.graduationproject.backend.backendwebsiteshoe.service.TrademarkService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Implement controller of trademark.
 *
 * @author Mai Ngoc Dinh.
 */
@CrossOrigin("*")
@RestController
@RequestMapping(value = "api/trademarks")
public class TrademarkController {

  @Autowired
  TrademarkService trademarkService;

  /**
   * Get all trademark.
   *
   * @return list object.
   */
  @GetMapping(value = "/init")
  public List<TrademarkEntity> selectAll() {
    return trademarkService.getAll();
  }

}
