package com.graduationproject.backend.backendwebsiteshoe.service;

import com.graduationproject.backend.backendwebsiteshoe.dto.RoleDTO;
import com.graduationproject.backend.backendwebsiteshoe.dto.UserRoleDTO;
import com.graduationproject.backend.backendwebsiteshoe.entity.RoleEntity;
import com.graduationproject.backend.backendwebsiteshoe.entity.UserEntity;
import com.graduationproject.backend.backendwebsiteshoe.model.UserDetailsModel;
import com.graduationproject.backend.backendwebsiteshoe.repository.RoleRepository;
import com.graduationproject.backend.backendwebsiteshoe.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Implement user service.
 *
 * @author Mai Ngoc Dinh
 */
@Service
public class UserService implements UserDetailsService {

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  /**
   * Locates the user based on the username. In the actual implementation, the search
   * may possibly be case sensitive, or case insensitive depending on how the
   * implementation instance is configured. In this case, the <code>UserDetails</code>
   * object that comes back may have a username that is of a different case than what
   * was actually requested..
   *
   * @param username the username identifying the user whose data is required.
   * @return a fully populated user record (never <code>null</code>)
   * @throws UsernameNotFoundException if the user could not be found or the user has no
   *                                   GrantedAuthority
   */
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserEntity user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("Username not found.!"));

    return this.toBuildUserDetailsModel(user);
  }

  /**
   * Get all role name.
   *
   * @param userId userId
   * @return user details model
   */
  public List<RoleDTO> getRoleOfUserLogin(Long userId) {
    return roleRepository.findAllByUserId(userId);
  }

  /**
   * Get all role name.
   *
   * @return user details model
   */
  public List<RoleEntity> getAllRole() {
    return roleRepository.findAll();
  }

  /**
   * Get all user name.
   *
   * @return find all user.
   */
  public Page<UserRoleDTO> getAll(Pageable pageable) {
    return userRepository.getAll(pageable);
  }

  /**
   * Delete user.
   *
   * @return find all user.
   */
  public Boolean deleteUserById(Long userId) {
    try {
      userRepository.deleteById(userId);
      return Boolean.TRUE;
    } catch (DataAccessException dataAccessException) {
      dataAccessException.printStackTrace();
      return Boolean.FALSE;
    }
  }

  /**
   * Delete user.
   *
   * @return find all user.
   */
  public UserEntity changeRoleName(Long userId, Long roleId) {
    Optional<UserEntity> userEntity = userRepository.findById(userId);
    userEntity.get().setRoleId(roleId);
    return userRepository.save(userEntity.get());
  }

  /**
   * Create user detail model.
   *
   * @param authentication authentication
   * @return user model
   */
  public UserDetailsModel getUserModel(Authentication authentication) {
    return (UserDetailsModel) authentication.getPrincipal();
  }

  /**
   * Check username is not exists.
   *
   * @param username username
   * @return TRUE if exist else FALSE.
   */
  public Boolean existsByUsername(String username) {
    return userRepository.existsByUsername(username);
  }

  /**
   * Check email is not exists.
   *
   * @param email email
   * @return TRUE if exist else FALSE.
   */
  public Boolean existsByEmail(String email) {
    return userRepository.existsByEmail(email);
  }

  /**
   * Insert all user with role.
   *
   * @param userEntityList userEntityList
   * @return TRUE if exist else FALSE.
   */
  public List<UserEntity> insert(List<UserEntity> userEntityList) {
    return userRepository.saveAll(userEntityList);
  }

  /**
   * Insert user with role.
   *
   * @param userEntity userEntity
   * @return TRUE if exist else FALSE.
   */
  public UserEntity insert(UserEntity userEntity) {
    return userRepository.save(userEntity);
  }

  /**
   * Map object DTO to model.
   *
   * @param user userDTO
   * @return user details model
   */
  private UserDetailsModel toBuildUserDetailsModel(@NotNull UserEntity user) {
    List<GrantedAuthority> authorityList = roleRepository.findAll().stream()
        .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
        .collect(Collectors.toList());

    return UserDetailsModel.builder()
        .userId(user.getUserId())
        .username(user.getUsername())
        .password(user.getUserPassword())
        .email(user.getUserEmail())
        .authorities(authorityList)
        .build();
  }
}
