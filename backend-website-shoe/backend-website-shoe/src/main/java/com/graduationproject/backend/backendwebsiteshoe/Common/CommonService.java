package com.graduationproject.backend.backendwebsiteshoe.Common;

import com.graduationproject.backend.backendwebsiteshoe.entity.BaseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CommonService {

    /**
     * Set common column when create entity.
     *
     * @param entity Entity
     * @param <T>    Entity
     */
    public <T extends BaseEntity> void setCommonCreatedEntity(T entity) {
        entity.setCreatedBy(1);
        entity.setCreatedDate(new Date());
        entity.setUpdatedBy(1);
        entity.setUpdatedDate(new Date());
        entity.setStatus(Boolean.TRUE);
    }

    /**
     * Set common column when create entity.
     *
     * @param entity Entity
     * @param <T>    Entity
     */
    public <T extends BaseEntity> void setCommonUpdateEntity(T entity) {
        entity.setCreatedBy(entity.getCreatedBy());
        entity.setCreatedDate(entity.getCreatedDate());
        entity.setUpdatedBy(1);
        entity.setUpdatedDate(new Date());
        entity.setStatus(Boolean.TRUE);
    }

    /**
     * Set name.
     *
     * @param name name
     * @return seo name
     */
    public String setSeo(String name) {
        return name.replace(" ", "-");
    }

    /**
     * Set status of item.
     *
     * @param status status
     * @return TRUE or FALSE
     */
    public Boolean setStatusOfItem(String status) {
        if (Constant.TRUE.equals(status)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
