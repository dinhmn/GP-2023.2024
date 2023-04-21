package com.graduationproject.backend.backendwebsiteshoe.service;

import com.graduationproject.backend.backendwebsiteshoe.utils.JasperUtils;
import com.graduationproject.backend.backendwebsiteshoe.model.OrderJasperModel;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

/**
 * Implement service of invoice.
 *
 * @author Mai Ngoc Dinh.
 */
@Service
public class InvoiceService {

  private static final String PREFIX_INVOICE = "order_";

  private static final String SUFFIX = ".pdf";

  private static final String INVOICE_TEMPLATE_PATH = "/META-INF/invoice-template.jrxml";

  private static final String LOGO_PATH = "/jasper/images/logo-invoice.png";

  /**
   * Generate invoice for each personal.
   *
   * @param orderJasperModel is a POJO contains all the data about the Invoice
   * @param locale           Locale is used to localize the PDF file (French, English...)
   * @return file pdf
   */
  public File generateInvoiceFor(OrderJasperModel orderJasperModel, Locale locale)
      throws IOException {
    // Create template order
    File pdfFile =
        File.createTempFile(PREFIX_INVOICE + orderJasperModel.getCustomerOrderCode(), SUFFIX);

    // Initiate a FileOutputStream
    try (FileOutputStream output = new FileOutputStream(pdfFile)) {

      // Load the invoice jrxml template
      JasperReport report = this.createTemplate();

      // Create parameter map.
      Map<String, Object> exportParameters = this.getParameters(orderJasperModel, locale);

      // Create an empty datasource.
      JRBeanCollectionDataSource dataSource =
          new JRBeanCollectionDataSource(Collections.singletonList("Invoice"));

      // Render the PDF file
      JasperUtils.renderAsPdf(report, exportParameters, dataSource, output);

      return pdfFile;

    } catch (final Exception exception) {
      exception.printStackTrace();
      throw new RuntimeException(exception);
    }
  }

  /**
   * Create template jasper report.
   *
   * @return jasper report.
   */
  private JasperReport createTemplate() throws JRException {
    InputStream pathJRInputStream = getClass().getResourceAsStream(INVOICE_TEMPLATE_PATH);
    JasperDesign jasperDesign = JRXmlLoader.load(pathJRInputStream);

    return JasperCompileManager.compileReport(jasperDesign);
  }

  /**
   * Fill template order parameters.
   *
   * @return jasper report.
   */
  private Map<String, Object> getParameters(OrderJasperModel orderJasperModel, Locale locale) {
    Map<String, Object> parameters = new HashMap<>();

    parameters.put("logo", getClass().getResourceAsStream(LOGO_PATH));
    parameters.put("order", orderJasperModel);
    parameters.put("LOCATE", locale);

    return parameters;
  }

}
