package com.graduationproject.backend.backendwebsiteshoe.controller;

import com.graduationproject.backend.backendwebsiteshoe.helper.OrderHelper;
import com.graduationproject.backend.backendwebsiteshoe.model.OrderJasperModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;

import com.graduationproject.backend.backendwebsiteshoe.service.InvoiceService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Implement controller of export PDF.
 *
 * @author Mai Ngoc Dinh.
 */
@RestController
@RequestMapping(value = "api/export")
public class ExportPDFController {

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
  public ResponseEntity<?> downloadInvoice() throws JRException, IOException {

////    JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(List.of(
////        OrderJasperModel.builder()
////            .productName("productName")
////            .productTotalPrice(1230345.1)
////            .productQuantity(35)
////            .productTotalPrice(1234345.12342)
////            .createdDate(new Date())
////            .build()), false);
//
//    Map<String, Object> parameters = new HashMap<>();
//    parameters.put("total", "7000");
//
//    JasperReport compileReport = JasperCompileManager
//        .compileReport(new FileInputStream("src/main/resources/META-INF/jasper.jrxml"));
//
//    JasperPrint jasperPrint =
//        JasperFillManager.fillReport(compileReport, parameters, beanCollectionDataSource);
//
//    JasperExportManager.exportReportToPdfFile(jasperPrint, System.currentTimeMillis() + ".pdf");
//
//    byte[] data = JasperExportManager.exportReportToPdf(jasperPrint);
//
//    System.err.println(data);
//    System.out.println(data);
//
//    HttpHeaders headers = new HttpHeaders();
//    headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");

    return new ResponseEntity<>(headers, HttpStatus.OK);
  }

  @PostMapping(value = "/export-pdf", produces = "application/pdf")
  public ResponseEntity<InputStreamResource> invoiceGenerate(
      @RequestParam(name = "code", defaultValue = "XYZ123456789") String code,
      @RequestParam(name = "lang", defaultValue = "en") String lang) throws IOException {
//    OrderJasperModel orderJasperModel = OrderJasperModel.builder()
//            .customerOrderCode(code)
//            .customerFirstName("Peter")
//            .customerLastName("Parker")
//            .customerPhone("0982211xxx")
//            .createdDate("10:47 p.m 2023-12-10")
//            .productName("productName")
//            .productTotalPrice(1230345.1)
//            .productQuantity(35)
//            .productTotalPrice(1234345.12342)
//            .build();
    File invoicePdf =
        invoiceService.generateInvoiceFor(orderJasperModel, Locale.forLanguageTag(lang));

    final HttpHeaders httpHeaders = getHttpHeaders(code, lang, invoicePdf);
    return new ResponseEntity<>(new InputStreamResource(new FileInputStream(invoicePdf)),
        httpHeaders, HttpStatus.OK);
  }

  private HttpHeaders getHttpHeaders(String code, String lang, File invoicePdf) {
    HttpHeaders respHeaders = new HttpHeaders();
    respHeaders.setContentType(MediaType.APPLICATION_PDF);
    respHeaders.setContentLength(invoicePdf.length());
    respHeaders.setContentDispositionFormData("attachment", String.format("%s-%s.pdf", code, lang));
    return respHeaders;
  }

}
