package com.graduationproject.backend.backendwebsiteshoe.service;

import com.graduationproject.backend.backendwebsiteshoe.dto.UserDTO;
import com.graduationproject.backend.backendwebsiteshoe.model.UserDetailsModel;
import com.graduationproject.backend.backendwebsiteshoe.repository.RoleRepository;
import com.graduationproject.backend.backendwebsiteshoe.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Config security project.
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
    UserDTO user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("Username not found.!"));

    return this.toBuildUserDetailsModel(user);
  }

  /**
   * Map object DTO to model.
   *
   * @param user userDTO
   * @return user details model
   */
  private UserDetailsModel toBuildUserDetailsModel(@NotNull UserDTO user) {
    List<GrantedAuthority> authorityList = roleRepository.findAll().stream()
        .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
        .collect(Collectors.toList());

    return UserDetailsModel.builder()
        .userId(user.getUserId())
        .username(user.getUsername())
        .password(user.getPassword())
        .email(user.getEmail())
        .authorities(authorityList)
        .build();
  }
}
