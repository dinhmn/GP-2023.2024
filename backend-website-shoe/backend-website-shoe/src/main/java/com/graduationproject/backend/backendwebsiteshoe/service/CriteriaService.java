package com.graduationproject.backend.backendwebsiteshoe.service;

import com.graduationproject.backend.backendwebsiteshoe.RequestJson;
import com.graduationproject.backend.backendwebsiteshoe.entity.JsonStationMappingPlans;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

/**
 * Create criteria service.
 *
 * @author Mai Ngoc Dinh.
 */
@Service
@Transactional
public class CriteriaService {

  @PersistenceContext
  private EntityManager entityManager;


  public List<JsonStationMappingPlansDto> getCreateNativeQuery(RequestJson requestJson) {
    StringBuilder sql = new StringBuilder();
    sql.append("SELECT ");
    sql.append("receivable_flag AS receivableFlag ");
    sql.append("ticket_exclusion_flag AS ticketExclusionFlag ");
    sql.append("buppan_exclusion_flag AS buppanExclusionFlag ");

    StringBuilder subquery = new StringBuilder();
    subquery.append("(SELECT STRING_AGG(tmp.json_value, ',') ")
        .append("FROM (SELECT json_value FROM json_station_mapping_plans ")
        .append("WHERE 1 = 1 ")
        .append("AND menu_no = ")
        .append(requestJson.getMenuNo());

    if (Strings.isNotEmpty(requestJson.getMenuNo())) {
      String plansQuery = requestJson.getPlans().replaceAll("-", ",");
      subquery.append(" AND plan_id IN (")
          .append(plansQuery)
          .append(")");
    }
    if (Strings.isNotEmpty(requestJson.getReceivableFlag())) {
      subquery.append(" AND receivable_flag = ")
          .append(requestJson.getReceivableFlag());
    }
    if (Strings.isNotEmpty(requestJson.getTicketExclusionFlag())) {
      subquery.append(" AND ticket_exclusion_flag = ")
          .append(requestJson.getTicketExclusionFlag());
    }
    if (Strings.isNotEmpty(requestJson.getBuppanExclusionFlag())) {
      subquery.append(" AND buppan_exclusion_flag = ")
          .append(requestJson.getBuppanExclusionFlag());
    }
    if (Strings.isNotEmpty(requestJson.getFamiExclusionFlag())) {
      subquery.append(" AND fami_exclusion_flag = ")
          .append(requestJson.getFamiExclusionFlag());
    }
    if (Strings.isNotEmpty(requestJson.getDesignatedDay())) {
      subquery.append(" AND menu_rc_dt_stt <= ")
          .append(requestJson.getDesignatedDay());
      subquery.append(" AND menu_rc_dt_end >= ")
          .append(requestJson.getDesignatedDay());
      subquery.append(" AND VALID_TERM_STT <= ")
          .append(requestJson.getDesignatedDay());
      subquery.append(" AND VALID_TERM_END >= ")
          .append(requestJson.getDesignatedDay());
      subquery.append(" AND PLAN_RC_DT_END >= ")
          .append(requestJson.getDesignatedDay());
    }
    subquery.append(" ORDER BY plan_id OFFSET ")
        .append(requestJson.getStartIndex())
        .append(" LIMIT ")
        .append(requestJson.getCount())
        .append(" ) tmp)]");

    sql.append(subquery);
    sql.append(" AS plans ");
    sql.append(" FROM json_station_mapping_plans")
        .append("WHERE 1 = 1 ")
        .append("AND menu_no = ")
        .append(requestJson.getMenuNo());

    if (Strings.isNotEmpty(requestJson.getMenuNo())) {
      String plansQuery = requestJson.getPlans().replaceAll("-", ",");
      sql.append(" AND plan_id IN (")
          .append(plansQuery)
          .append(")");
    }
    if (Strings.isNotEmpty(requestJson.getReceivableFlag())) {
      sql.append(" AND receivable_flag = ")
          .append(requestJson.getReceivableFlag());
    }
    if (Strings.isNotEmpty(requestJson.getTicketExclusionFlag())) {
      sql.append(" AND ticket_exclusion_flag = ")
          .append(requestJson.getTicketExclusionFlag());
    }
    if (Strings.isNotEmpty(requestJson.getBuppanExclusionFlag())) {
      sql.append(" AND buppan_exclusion_flag = ")
          .append(requestJson.getBuppanExclusionFlag());
    }
    if (Strings.isNotEmpty(requestJson.getFamiExclusionFlag())) {
      sql.append(" AND fami_exclusion_flag = ")
          .append(requestJson.getFamiExclusionFlag());
    }
    if (Strings.isNotEmpty(requestJson.getDesignatedDay())) {
      sql.append(" AND menu_rc_dt_stt <= ")
          .append(requestJson.getDesignatedDay());
      sql.append(" AND menu_rc_dt_end >= ")
          .append(requestJson.getDesignatedDay());
      sql.append(" AND VALID_TERM_STT <= ")
          .append(requestJson.getDesignatedDay());
      sql.append(" AND VALID_TERM_END >= ")
          .append(requestJson.getDesignatedDay());
      sql.append(" AND PLAN_RC_DT_END >= ")
          .append(requestJson.getDesignatedDay());
    }
    sql.append(" GROUP BY receivable_flag,ticket_exclusion_flag,buppan_exclusion_flag ;");

    Query query = entityManager.createNativeQuery(String.valueOf(sql), JsonStationMappingPlansDto.class);
    query.setFirstResult(requestJson.getStartIndex());
    query.setMaxResults(requestJson.getCount());
    System.out.println(query.getResultList());
    return query.getResultList();
  }

  public JsonStationMappingPlans getItemProduct(RequestJson requestJson) {
    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    CriteriaQuery<JsonStationMappingPlansDto> createQuery =
        builder.createQuery(JsonStationMappingPlansDto.class);
    Root<JsonStationMappingPlans> productRoot = createQuery.from(JsonStationMappingPlans.class);
    List<Predicate> predicatesList = getConditionQueryParams(requestJson, productRoot, builder);

    Predicate[] predArray = new Predicate[predicatesList.size()];
    predicatesList.toArray(predArray);
    createQuery.where(predArray);
    createQuery.groupBy(productRoot.get("receivableFlag"), productRoot.get("ticketExclusionFlag"),
        productRoot.get("buppanExclusionFlag"));


    Subquery<String> subquery = createQuery.subquery(String.class);
    Root<JsonStationMappingPlans> subRoot = subquery.from(JsonStationMappingPlans.class);
    subquery.select(subRoot.get("jsonValue"));
    List<Predicate> predicatesSubqueryList =
        getConditionQueryParams(requestJson, productRoot, builder);

    Predicate[] predSubqueryArray = new Predicate[predicatesSubqueryList.size()];
    predicatesList.toArray(predSubqueryArray);
    subquery.where(predArray);


    createQuery.select(
        builder.construct(
            JsonStationMappingPlansDto.class,
            productRoot.get("receivableFlag"),
            productRoot.get("ticketExclusionFlag"),
            productRoot.get("buppanExclusionFlag"),
            subquery.getSelection()
        )
    );

    System.out.println(entityManager.createQuery(createQuery).getResultList());
    return null;
  }

  private List<Predicate> getConditionQueryParams(RequestJson requestJson,
                                                  Root<JsonStationMappingPlans> productRoot,
                                                  CriteriaBuilder builder) {
    List<Predicate> predicatesList = new ArrayList<>();
    predicatesList.add(builder.equal(productRoot.get("menuNo"), requestJson.getMenuNo()));
    List<String> plansQuery = List.of(requestJson.getPlans().split("-"));

    if (Strings.isNotEmpty(requestJson.getMenuNo())) {
      predicatesList.add(productRoot.get("planId").in(plansQuery));
    }
    if (Strings.isNotEmpty(requestJson.getReceivableFlag())) {
      predicatesList.add(
          builder.equal(productRoot.get("receivableFlag"), requestJson.getReceivableFlag()));
    }
    if (Strings.isNotEmpty(requestJson.getTicketExclusionFlag())) {
      predicatesList.add(builder.equal(productRoot.get("ticketExclusionFlag"),
          requestJson.getTicketExclusionFlag()));
    }
    if (Strings.isNotEmpty(requestJson.getBuppanExclusionFlag())) {
      predicatesList.add(builder.equal(productRoot.get("buppanExclusionFlag"),
          requestJson.getBuppanExclusionFlag()));
    }
    if (Strings.isNotEmpty(requestJson.getFamiExclusionFlag())) {
      predicatesList.add(
          builder.equal(productRoot.get("famiExclusionFlag"), requestJson.getFamiExclusionFlag()));
    }
    if (Strings.isNotEmpty(requestJson.getDesignatedDay())) {
      predicatesList.add(builder.lessThanOrEqualTo(productRoot.get("menuRcDtStt"),
          requestJson.getDesignatedDay()));
      predicatesList.add(builder.greaterThanOrEqualTo(productRoot.get("menRcDtEnd"),
          requestJson.getDesignatedDay()));
      predicatesList.add(builder.lessThanOrEqualTo(productRoot.get("validTermStt"),
          requestJson.getDesignatedDay()));
      predicatesList.add(builder.greaterThanOrEqualTo(productRoot.get("validTermEnd"),
          requestJson.getDesignatedDay()));
      predicatesList.add(builder.greaterThanOrEqualTo(productRoot.get("planRcDtEnd"),
          requestJson.getDesignatedDay()));
    }
    return predicatesList;
  }


  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class JsonStationMappingPlansDto {
    private String receivableFlag;
    private String ticketExclusionFlag;
    private String buppanExclusionFlag;
    private String plans;
  }
}
