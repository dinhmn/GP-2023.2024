package com.graduationproject.backend.backendwebsiteshoe.repository;

import com.graduationproject.backend.backendwebsiteshoe.entity.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Implement repository of user.
 *
 * @author Mai Ngoc Dinh
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

  /**
   * Get one user.
   *
   * @param username username
   * @return user.
   */
  @Query(value = "SELECT * FROM tbl_user usr WHERE usr.username = ?1", nativeQuery = true)
  Optional<UserEntity> findByUsername(String username);

  /**
   * Get TRUE or FALSE.
   *
   * @param username username
   * @return TRUE IF exists ELSE FALSE.
   */
  @Query(value = "SELECT CASE WHEN COUNT(usr) > 0 THEN TRUE ELSE FALSE END"
      + " FROM UserEntity usr WHERE LOWER(usr.username) LIKE LOWER(?1)")
  Boolean existsByUsername(String username);

  /**
   * Get TRUE or FALSE.
   *
   * @param email email
   * @return TRUE IF exists ELSE FALSE.
   */
  @Query(value = "SELECT CASE WHEN COUNT(usr) > 0 THEN TRUE ELSE FALSE END"
      + " FROM UserEntity usr "
      + " INNER JOIN UserInformationEntity usrIn ON usr.profileId = usrIn.userInformationId"
      + " WHERE LOWER(usrIn.email) LIKE LOWER(?1)")
  Boolean existsByEmail(String email);

}
