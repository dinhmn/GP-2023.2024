package com.graduationproject.backend.backendwebsiteshoe.helper;

import com.graduationproject.backend.backendwebsiteshoe.model.MailRequestModel;
import com.graduationproject.backend.backendwebsiteshoe.model.MailResponseModel;
import com.graduationproject.backend.backendwebsiteshoe.service.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Implement service of send mail.
 *
 * @author Mai Ngoc Dinh
 */
@Component
public class SendMailHelper {

  @Autowired
  SendMailService sendMailService;

  /**
   * Send mail.
   *
   * @param mailRequestModel mailRequestModel
   * @return mail response
   */
  public Boolean sendMail(MailRequestModel mailRequestModel) {
    Map<String, Object> model = new HashMap<>();
    model.put("Name", mailRequestModel.getMailName());
    model.put("location", "Bangkok, Hanoi, Jakarta");
    MailResponseModel response = sendMailService.sendMail(mailRequestModel, model);

    if (Boolean.FALSE.equals(response.getStatus())) {
      return Boolean.FALSE;
    }
    return Boolean.TRUE;
  }
}
