package com.graduationproject.backend.backendwebsiteshoe.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpForm {

  private String username;

  private String password;

  private String email;

}
