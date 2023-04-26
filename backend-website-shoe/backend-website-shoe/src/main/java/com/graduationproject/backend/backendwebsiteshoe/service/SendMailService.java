package com.graduationproject.backend.backendwebsiteshoe.service;

import com.graduationproject.backend.backendwebsiteshoe.model.MailRequestModel;
import com.graduationproject.backend.backendwebsiteshoe.model.MailResponseModel;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

/**
 * Implement service of send mail.
 *
 * @author Mai Ngoc Dinh
 */
@Service
public class SendMailService {

  @Autowired
  JavaMailSender javaMailSender;

  @Autowired
  Configuration config;

  /**
   * Send mail.
   *
   * @param mailRequestModel mailRequestModel
   * @param model            model
   * @return mail response
   */
  public MailResponseModel sendMail(MailRequestModel mailRequestModel, Map<String, Object> model) {
    MailResponseModel response = new MailResponseModel();
    MimeMessage message = javaMailSender.createMimeMessage();

    try {
      // set mediaType
      MimeMessageHelper helper =
          new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
              StandardCharsets.UTF_8.name());
      // add attachment
      helper.addAttachment("logo.pdf", new ClassPathResource("./templates/uploads/logo.pdf"));

      Template template = config.getTemplate("email-template.ftl");
      String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

      helper.setTo(mailRequestModel.getMailTo());
      helper.setText(html, true);
      helper.setSubject(mailRequestModel.getMailSubject());
      helper.setFrom(mailRequestModel.getMailFrom());
      javaMailSender.send(message);

      response.setMessage("Mail send to : " + mailRequestModel.getMailTo());
      response.setStatus(Boolean.TRUE);

    } catch (IOException | TemplateException | MessagingException e) {
      response.setMessage("Mail Sending failure : " + e.getMessage());
      response.setStatus(Boolean.FALSE);
    }

    return response;
  }

}
