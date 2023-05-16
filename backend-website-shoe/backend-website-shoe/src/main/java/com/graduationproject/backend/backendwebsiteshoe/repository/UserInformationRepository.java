package com.graduationproject.backend.backendwebsiteshoe.repository;

import com.graduationproject.backend.backendwebsiteshoe.entity.UserInformationEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Implement repository of user information.
 *
 * @author Mai Ngoc Dinh
 */
@Repository
public interface UserInformationRepository extends JpaRepository<UserInformationEntity, Long> {
  Optional<UserInformationEntity> findByUserId(Long userId);
}
