package com.graduationproject.backend.backendwebsiteshoe.repository;

import com.graduationproject.backend.backendwebsiteshoe.entity.RoleEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Implement repository of role.
 *
 * @author Mai Ngoc Dinh
 */
@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

  /**
   * Get one role.
   *
   * @param roleName roleName
   * @return role.
   */
  @Query(value = "SELECT * FROM tbl_role role WHERE role.role_name = ?1", nativeQuery = true)
  Optional<RoleEntity> findByRoleName(String roleName);
}
