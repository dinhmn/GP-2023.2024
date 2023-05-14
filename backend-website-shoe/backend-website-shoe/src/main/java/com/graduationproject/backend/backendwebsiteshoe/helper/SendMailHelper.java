package com.graduationproject.backend.backendwebsiteshoe.helper;

import com.graduationproject.backend.backendwebsiteshoe.model.MailRequestModel;
import com.graduationproject.backend.backendwebsiteshoe.model.MailResponseModel;
import com.graduationproject.backend.backendwebsiteshoe.model.OrderJasperModel;
import com.graduationproject.backend.backendwebsiteshoe.service.InvoiceService;
import com.graduationproject.backend.backendwebsiteshoe.service.SendMailService;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
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

  @Autowired
  OrderHelper orderHelper;

  @Autowired
  InvoiceService invoiceService;

  /**
   * Send mail.
   *
   * @param mailRequestModel mailRequestModel
   * @return mail response
   */
  public Boolean sendMail(MailRequestModel mailRequestModel, Long orderId) throws IOException {
    OrderJasperModel orderJasperModel = orderHelper.getAllByOrderId(orderId);

    invoiceService.generateInvoiceFor(orderJasperModel, Locale.forLanguageTag("en"));

    Map<String, Object> model = new HashMap<>();
    model.put("Name", mailRequestModel.getMailName());
    model.put("location", "HaNoi - 2023");
    MailResponseModel response = sendMailService.sendMail(mailRequestModel, model, orderId);

    if (Boolean.FALSE.equals(response.getStatus())) {
      return Boolean.FALSE;
    }
    return Boolean.TRUE;
  }
}
