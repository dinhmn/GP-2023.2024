package com.graduationproject.backend.backendwebsiteshoe.controller;

import com.graduationproject.backend.backendwebsiteshoe.forms.LoginForm;
import com.graduationproject.backend.backendwebsiteshoe.model.UserDetailsModel;
import com.graduationproject.backend.backendwebsiteshoe.repository.RoleRepository;
import com.graduationproject.backend.backendwebsiteshoe.repository.UserRepository;
import com.graduationproject.backend.backendwebsiteshoe.response.JwtResponse;
import com.graduationproject.backend.backendwebsiteshoe.security.jwt.JwtUtils;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Implement controller of category.
 *
 * @author Mai Ngoc Dinh.
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

//  @Autowired
//  AuthenticationManager authenticationManager;
//
//  @Autowired
//  UserRepository userRepository;
//
//  @Autowired
//  RoleRepository roleRepository;
//
//  @Autowired
//  PasswordEncoder encoder;
//
//  @Autowired
//  JwtUtils jwtUtils;
//
//  /**
//   * Login with information from UI.
//   *
//   * @return response entity.
//   */
//  @PostMapping("/signin")
//  public ResponseEntity<?> authenticateUser(@Validated @RequestBody LoginForm loginRequest) {
//
//    Authentication authentication = authenticationManager.authenticate(
//        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
//            loginRequest.getPassword()));
//
//    SecurityContextHolder.getContext().setAuthentication(authentication);
//    String jwt = jwtUtils.generateJwtToken(authentication);
//
//    UserDetailsModel userDetails = (UserDetailsModel) authentication.getPrincipal();
//    List<String> roles = userDetails.getAuthorities().stream()
//        .map(GrantedAuthority::getAuthority)
//        .collect(Collectors.toList());
//
//    return ResponseEntity.ok(new JwtResponse(jwt,
//        userDetails.getUserId(),
//        userDetails.getUsername(),
//        userDetails.getEmail(),
//        roles));
//  }

//  @PostMapping("/signup")
//  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
//    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
//      return ResponseEntity
//          .badRequest()
//          .body(new MessageResponse("Error: Username is already taken!"));
//    }
//
//    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
//      return ResponseEntity
//          .badRequest()
//          .body(new MessageResponse("Error: Email is already in use!"));
//    }
//
//    // Create new user's account
//    User user = new User(signUpRequest.getUsername(),
//        signUpRequest.getEmail(),
//        encoder.encode(signUpRequest.getPassword()));
//
//    Set<String> strRoles = signUpRequest.getRole();
//    Set<Role> roles = new HashSet<>();
//
//    if (strRoles == null) {
//      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//      roles.add(userRole);
//    } else {
//      strRoles.forEach(role -> {
//        switch (role) {
//          case "admin":
//            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
//                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//            roles.add(adminRole);
//
//            break;
//          case "mod":
//            Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
//                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//            roles.add(modRole);
//
//            break;
//          default:
//            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//            roles.add(userRole);
//        }
//      });
//    }
//
//    user.setRoles(roles);
//    userRepository.save(user);
//
//    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
//  }

}
