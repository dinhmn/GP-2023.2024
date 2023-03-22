package com.graduationproject.backend.backendwebsiteshoe.dto;

import java.util.Date;

public interface ICategory {
    Long getCategoryId();

    Long getTrademarkId();

    String getTrademarkName();

    String getCategoryDescription();

    String getCategoryName();

    String getCategorySeo();

    Date getCreatedDate();

    Date getUpdatedDate();

    String getCategoryStatus();
}
