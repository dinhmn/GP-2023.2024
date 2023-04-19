package com.graduationproject.backend.backendwebsiteshoe.controller;

import com.graduationproject.backend.backendwebsiteshoe.entity.ProductEntity;
import com.graduationproject.backend.backendwebsiteshoe.helper.SendMailHelper;
import com.graduationproject.backend.backendwebsiteshoe.model.MailRequestModel;
import com.graduationproject.backend.backendwebsiteshoe.model.ProductModel;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Implement controller of product.
 *
 * @author Mai Ngoc Dinh.
 */
@CrossOrigin("*")
@RestController
@RequestMapping(value = "api/send-mail")
public class SendMailController {

    @Autowired
    SendMailHelper sendMailHelper;

    /**
     * Send mail.
     *
     * @param requestModel requestModel
     * @return response entity
     */
    @PostMapping(value = "/")
    public ResponseEntity<?> registerProduct(@RequestBody MailRequestModel requestModel){
        Boolean statusSend = sendMailHelper.sendMail(requestModel);

        if (Boolean.FALSE.equals(statusSend)) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(statusSend, HttpStatus.OK);
    }
}
