package com.graduationproject.backend.backendwebsiteshoe.controller;

import com.graduationproject.backend.backendwebsiteshoe.RequestJson;
import com.graduationproject.backend.backendwebsiteshoe.common.Constant;
import com.graduationproject.backend.backendwebsiteshoe.entity.JsonStationMappingPlans;
import com.graduationproject.backend.backendwebsiteshoe.forms.ArticleFormPage;
import com.graduationproject.backend.backendwebsiteshoe.service.CriteriaService;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

  @Autowired
  private CriteriaService criteriaService;

  @GetMapping(value = "api/get/json")
  public List<CriteriaService.JsonStationMappingPlansDto> selectAll() {
    RequestJson requestJson = RequestJson.builder()
        .designatedDay("31/05/2023")
        .famiExclusionFlag("1")
        .buppanExclusionFlag("1")
        .ticketExclusionFlag("1")
        .receivableFlag("1")
        .plans("22002220-2-3-5")
        .menuNo("22002220")
        .count(35)
        .startIndex(1)
        .build();
    return criteriaService.getCreateNativeQuery(requestJson);
  }


}
