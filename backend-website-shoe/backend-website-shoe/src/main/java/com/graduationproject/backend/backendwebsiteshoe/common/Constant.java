package com.graduationproject.backend.backendwebsiteshoe.common;

/**
 * Define constant.
 *
 * @author Mai Ngoc Dinh
 */
public interface Constant {

  Integer ZERO = 0;

  String INSERT = "insert";

  String UPDATE = "update";

  String PATTERN_DATETIME = "dd-MM-yyyy";

  String PATTERN_DATETIME_HOURS = "HH:mm dd-MM-yyyy";

  String TRUE = "1";

  String FALSE = "0";

  final String DEFAULT_PAGE_NUMBER = "0";

  final String DEFAULT_PAGE_SIZE = "10";

  final String DEFAULT_SORT_DIRECTION = "ASC";

  String EMPTY_SPACE = "";

  Long EMPTY_NULL = null;

  String NULL = null;

  String PATTERN_HH_MM = "HH:mm";

  String PATTERN_ANT_AUTH = "/api/auth/**";

  String PATTERN_ANT_USER = "/api/test/**";

}
