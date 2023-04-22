package com.graduationproject.backend.backendwebsiteshoe.controller;

import com.graduationproject.backend.backendwebsiteshoe.helper.OrderHelper;
import com.graduationproject.backend.backendwebsiteshoe.model.OrderJasperModel;
import com.graduationproject.backend.backendwebsiteshoe.service.InvoiceService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Implement controller of export PDF.
 *
 * @author Mai Ngoc Dinh.
 */
@CrossOrigin("*")
@RestController
@RequestMapping(value = "api/export")
public class ExportPdfController {

  @Autowired
  OrderHelper orderHelper;

  @Autowired
  InvoiceService invoiceService;

  /**
   * Export PDF.
   *
   * @return file PDF.
   */
  @GetMapping(value = "/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
  public ResponseEntity<?> downloadInvoice() {
    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");

    return new ResponseEntity<>(headers, HttpStatus.OK);
  }

  /**
   * Export PDF.
   *
   * @param orderId orderId
   * @param lang    lang
   * @return response input stream resource
   */
  @PostMapping(value = "/export-pdf", produces = "application/pdf")
  public ResponseEntity<InputStreamResource> invoiceGenerate(
      @RequestParam(name = "code", defaultValue = "4") Long orderId,
      @RequestParam(name = "lang", defaultValue = "en") String lang)
      throws IOException, FileNotFoundException {

    OrderJasperModel orderJasperModel = orderHelper.getAllByOrderId(orderId);

    File invoicePdf =
        invoiceService.generateInvoiceFor(orderJasperModel, Locale.forLanguageTag(lang));

    final HttpHeaders httpHeaders =
        getHttpHeaders(orderJasperModel.getOrderCode(), lang, invoicePdf);
    InputStreamResource inputStreamResource = null;
    try {
      FileInputStream fileInputStream = new FileInputStream(invoicePdf);
      inputStreamResource = new InputStreamResource(fileInputStream);
    } catch (FileNotFoundException fileNotFoundException) {
      fileNotFoundException.printStackTrace();
    }

    return new ResponseEntity<>(inputStreamResource, httpHeaders, HttpStatus.OK);
  }

  /**
   * Http header.
   *
   * @param code       code
   * @param lang       lang
   * @param invoicePdf invoicePdf
   * @return http header
   */
  private HttpHeaders getHttpHeaders(String code, String lang, File invoicePdf) {
    HttpHeaders respHeaders = new HttpHeaders();
    respHeaders.setContentType(MediaType.APPLICATION_PDF);
    respHeaders.setContentLength(invoicePdf.length());
    respHeaders.setContentDispositionFormData("attachment", String.format("%s-%s.pdf", code, lang));
    return respHeaders;
  }

}
