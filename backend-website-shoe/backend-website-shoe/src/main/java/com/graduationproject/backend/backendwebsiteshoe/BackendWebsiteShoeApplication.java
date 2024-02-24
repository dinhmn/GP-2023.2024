package com.graduationproject.backend.backendwebsiteshoe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Application backend.
 *
 * @author Mai Ngoc Dinh.
 */
@SpringBootApplication
public class BackendWebsiteShoeApplication {

  public static void main(String[] args) {
    new SpringApplicationBuilder(BackendWebsiteShoeApplication.class)
        .web(WebApplicationType.NONE)
        .run(args);
  }

}
