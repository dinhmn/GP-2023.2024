package com.graduationproject.backend.backendwebsiteshoe.helper;

import com.graduationproject.backend.backendwebsiteshoe.common.CommonService;
import com.graduationproject.backend.backendwebsiteshoe.common.Constant;
import com.graduationproject.backend.backendwebsiteshoe.common.User;
import com.graduationproject.backend.backendwebsiteshoe.dto.RoleDTO;
import com.graduationproject.backend.backendwebsiteshoe.entity.UserEntity;
import com.graduationproject.backend.backendwebsiteshoe.entity.UserInformationEntity;
import com.graduationproject.backend.backendwebsiteshoe.forms.LoginForm;
import com.graduationproject.backend.backendwebsiteshoe.forms.SignUpForm;
import com.graduationproject.backend.backendwebsiteshoe.model.UserDetailsModel;
import com.graduationproject.backend.backendwebsiteshoe.response.JwtResponse;
import com.graduationproject.backend.backendwebsiteshoe.response.SignUpResponse;
import com.graduationproject.backend.backendwebsiteshoe.security.jwt.JwtUtils;
import com.graduationproject.backend.backendwebsiteshoe.service.UserInformationService;
import com.graduationproject.backend.backendwebsiteshoe.service.UserService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * Implement helper of helper.
 *
 * @author Mai Ngoc Dinh
 */
@Component
public class AuthHelper {

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  CommonService commonService;

  @Autowired
  UserService userService;

  @Autowired
  UserInformationService userInformationService;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  /**
   * Login with information from UI.
   *
   * @param loginForm loginForm
   * @return jwt reponse.
   */
  public JwtResponse login(@NonNull LoginForm loginForm) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginForm.getUsername(),
            loginForm.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    // Generate jwt
    String jwt = jwtUtils.generateJwtToken(authentication);

    UserDetailsModel userDetails = userService.getUserModel(authentication);

    List<String> roles = userService.getRoleOfUserLogin(userDetails.getUserId()).stream()
        .map(RoleDTO::getRoleName)
        .toList();

    return new JwtResponse(jwt,
        userDetails.getUserId(),
        userDetails.getUsername(),
        userDetails.getEmail(),
        roles);
  }

  /**
   * Login with information from UI.
   *
   * @return response entity.
   */
  public ResponseEntity<SignUpResponse> registerUser(SignUpForm signUpForm) {
    if (userService.existsByUsername(signUpForm.getUsername())) {
      return ResponseEntity
          .badRequest()
          .body(new SignUpResponse("Error: Username is already taken!"));
    }

    if (userService.existsByEmail(signUpForm.getEmail())) {
      return ResponseEntity
          .badRequest()
          .body(new SignUpResponse("Error: Email is already in use!"));
    }
    if (!CollectionUtils.isEmpty(signUpForm.getRoleIdList())) {
      List<UserEntity> userEntityList = new ArrayList<>();
      for (int i = 0; i < signUpForm.getRoleIdList().size(); i++) {
        Long roleId = signUpForm.getRoleIdList().get(i);
        UserEntity userEntity = this.toBuildUserEntity(signUpForm, roleId);

        userEntityList.add(userEntity);
        Long userId = userService.insert(userEntity).getUserId();
        UserInformationEntity userInformationEntity =
            this.toBuildUserInformationEntity(signUpForm, userId);
        userInformationService.insert(userInformationEntity);
      }

      return ResponseEntity.ok(new SignUpResponse("User registered successfully!"));
    }

    return ResponseEntity.ok(new SignUpResponse("User register error!"));
  }

  /**
   * To build signUpForm entity.
   *
   * @param signUpForm userDTO
   * @param roleId     roleId
   * @return signUpForm details model
   */
  private UserEntity toBuildUserEntity(SignUpForm signUpForm, Long roleId) {
    UserEntity userEntity = new UserEntity();
    userEntity.setRoleId(roleId);
    userEntity.setUsername(signUpForm.getUsername());
    userEntity.setUserPassword(passwordEncoder.encode(signUpForm.getPassword()));
    userEntity.setUserEmail(signUpForm.getEmail());
    commonService.setCommonCreatedEntity(userEntity);
    return userEntity;
  }

  /**
   * To build signUpForm entity.
   *
   * @param signUpForm userDTO
   * @param userId     userId
   * @return signUpForm details model
   */
  private UserInformationEntity toBuildUserInformationEntity(SignUpForm signUpForm, Long userId) {
    UserInformationEntity userInformationEntity = new UserInformationEntity();
    userInformationEntity.setUserId(userId);
    userInformationEntity.setAddress(Constant.EMPTY_SPACE);
    userInformationEntity.setEmail(signUpForm.getEmail());
    userInformationEntity.setFirstName(signUpForm.getFirstName());
    userInformationEntity.setLastName(signUpForm.getLastName());
    userInformationEntity.setPhone(Constant.EMPTY_SPACE);
    userInformationEntity.setUserCode(User.USER.getCode());
    commonService.setCommonCreatedEntity(userInformationEntity);
    return userInformationEntity;
  }
}
