package com.graduationproject.backend.backendwebsiteshoe.service;

import com.graduationproject.backend.backendwebsiteshoe.entity.SubscribeEntity;
import com.graduationproject.backend.backendwebsiteshoe.repository.SubscribeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Implement service of subscribe.
 *
 * @author Mai Ngoc Dinh
 */
@Service
public class SubscribeService {

  @Autowired
  SubscribeRepository subscribeRepository;

  /**
   * Get all subscribe.
   *
   * @param pageable pageable
   * @return list of entity subscribe.
   */
  public Page<SubscribeEntity> getAll(Pageable pageable) {
    return subscribeRepository.findAll(pageable);
  }

  /**
   * Get all subscribe.
   *
   * @param searchValue searchValue
   * @param pageable    pageable
   * @return list of entity subscribe.
   */
  public Page<SubscribeEntity> getAll(String searchValue, Pageable pageable) {
    return subscribeRepository.findAllSubscribe(searchValue, pageable);
  }

  /**
   * Insert subscribe.
   *
   * @param subscribeEntity subscribeEntity
   * @return entity subscribe.
   */
  public SubscribeEntity insert(SubscribeEntity subscribeEntity) {
    try {
      return subscribeRepository.save(subscribeEntity);
    } catch (DataAccessException dataAccessException) {
      dataAccessException.printStackTrace();
    }
    return null;
  }
}
