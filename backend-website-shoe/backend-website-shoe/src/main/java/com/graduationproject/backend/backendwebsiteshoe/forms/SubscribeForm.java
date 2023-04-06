package com.graduationproject.backend.backendwebsiteshoe.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Form response pageable.
 *
 * @author Mai Ngoc Dinh
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubscribeForm {

  private String subscribeEmail;

}
