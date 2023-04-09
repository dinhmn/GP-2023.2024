package com.graduationproject.backend.backendwebsiteshoe.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Config security project.
 *
 * @author Mai Ngoc Dinh
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//  @Autowired
//  UserDetailsServiceImpl userDetailsService;
//
//  @Autowired
//  AuthEntryPointJwt unauthorizedHandler;
}
