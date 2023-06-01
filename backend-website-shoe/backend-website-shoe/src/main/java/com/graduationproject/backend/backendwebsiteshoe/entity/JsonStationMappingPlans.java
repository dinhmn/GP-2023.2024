package com.graduationproject.backend.backendwebsiteshoe.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Contact entity.
 *
 * @author Mai Ngoc Dinh.
 */
@EqualsAndHashCode(callSuper = false)
@Entity
@Data
@Table(name = "json_station_mapping_plans")
public class JsonStationMappingPlans implements Serializable {

  private static final long serialVersionUID = 1L;

  public JsonStationMappingPlans() {

    super();
  }

  @Id
  @Column(name = "menu_no", nullable = false)
  private String menuNo;

  @Column(name = "plan_id", nullable = false)
  private String planId;

  @Column(name = "receivable_flag", nullable = false)
  private String receivableFlag;

  @Column(name = "ticket_exclusion_flag", nullable = false)
  private String ticketExclusionFlag;

  @Column(name = "buppan_exclusion_flag")
  private String buppanExclusionFlag;

  @Column(name = "fami_exclusion_flag")
  private String famiExclusionFlag;

  @Column(name = "menu_rc_dt_stt")
  private String menuRcDtStt;

  @Column(name = "menu_rc_dt_end")
  private String menRcDtEnd;

  @Column(name = "VALID_TERM_STT")
  private String validTermStt;

  @Column(name = "VALID_TERM_END")
  private String validTermEnd;

  @Column(name = "PLAN_RC_DT_END")
  private String planRcDtEnd;

  @Column(name = "designated_day")
  private String designatedDay;

  @Column(name = "json_value")
  private String jsonValue;

}
