package com.graduationproject.backend.backendwebsiteshoe.service;

import com.graduationproject.backend.backendwebsiteshoe.model.OrderJasperModel;
import com.graduationproject.backend.backendwebsiteshoe.utils.JasperUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.springframework.stereotype.Service;


/**
 * Implement service of invoice.
 *
 * @author Mai Ngoc Dinh.
 */
@Service
public class InvoiceService {

  private static final String TARGET_SOURCE = "D:\\image\\export-pdf";

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
        File.createTempFile(PREFIX_INVOICE + orderJasperModel.getOrderCode(), SUFFIX);
    String origPath = pdfFile.getCanonicalPath();
    String parentPath = pdfFile.getParent();
    String fileName = pdfFile.getName();
    String replacePath = origPath.replace(parentPath, TARGET_SOURCE);
    String pathFinish = replacePath.replace(fileName, "order_" + orderJasperModel.getOrderCode() + SUFFIX);
    // Initiate a FileOutputStream
    try (FileOutputStream output = new FileOutputStream(pathFinish)) {

      // Load the invoice jrxml template
      JasperReport report = this.createTemplate();

      // Create parameter map.
      Map<String, Object> exportParameters = this.getParameters(orderJasperModel, locale);

      // Create an empty datasource.
      JRBeanCollectionDataSource dataSource =
          new JRBeanCollectionDataSource(Collections.singletonList("Invoice"));

      exportParameters.put(JRParameter.IS_IGNORE_PAGINATION, true);
      // Render the PDF file
      JasperUtils.renderAsPdf(report, exportParameters, dataSource, output);

      return pdfFile;

    } catch (final Exception exception) {
      exception.printStackTrace();
      throw new RuntimeException(exception);
    }
  }

  /**
   * Export invoice for each personal.
   *
   * @param orderJasperModel is a POJO contains all the data about the Invoice
   * @param locale           Locale is used to localize the PDF file (French, English...)
   * @return file pdf
   */
  public void exportPdf(OrderJasperModel orderJasperModel, Locale locale)
      throws IOException {
    // Create template order
    File pdfFile =
        File.createTempFile(PREFIX_INVOICE + orderJasperModel.getOrderCode(), SUFFIX);
    String origPath = pdfFile.getCanonicalPath();
    String parentPath = pdfFile.getParent();
    String fileName = pdfFile.getName();
    String replacePath = origPath.replace(parentPath, TARGET_SOURCE);
    String pathFinish = replacePath.replace(fileName, "order_" + orderJasperModel.getOrderCode() + SUFFIX);
    // Initiate a FileOutputStream
    try (FileOutputStream output = new FileOutputStream(pathFinish)) {

      // Load the invoice jrxml template
      JasperReport report = this.createTemplate();

      // Create parameter map.
      Map<String, Object> exportParameters = this.getParameters(orderJasperModel, locale);

      // Create an empty datasource.
      JRBeanCollectionDataSource dataSource =
          new JRBeanCollectionDataSource(Collections.singletonList("Invoice"));

      // Render the PDF file
      JasperUtils.renderAsPdf(report, exportParameters, dataSource, output);
      JasperPrint print =
          JasperFillManager.fillReport(report, exportParameters, dataSource);

      JasperExportManager.exportReportToPdf(print);

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
    JasperDesign jasperDesign = new JasperDesign();
    try (InputStream pathJRInputStream = getClass().getResourceAsStream(INVOICE_TEMPLATE_PATH)) {
      jasperDesign = JRXmlLoader.load(pathJRInputStream);
    } catch (IOException exception) {
      exception.printStackTrace();
    }

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
