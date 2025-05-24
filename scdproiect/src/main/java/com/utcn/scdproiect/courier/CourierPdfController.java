package com.utcn.scdproiect.courier;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.utcn.scdproiect.pkg.PackageService;
import com.utcn.scdproiect.courier.CourierRepository;
import com.utcn.scdproiect.email.EmailService;
import com.utcn.scdproiect.courier.Courier;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/courier")
public class CourierPdfController {

      @Autowired
    private PackageService packageService;

    @Autowired
    private CourierRepository courierRepository;

    // Serviciu de trimitere email (metoda sendEmails preexistentă)
    @Autowired
    private EmailService emailService;

  @GetMapping("/{id}/packages/pdf")
  public void downloadPdf(
      @PathVariable Integer id,
      HttpServletResponse response,
      Authentication auth
  ) throws IOException, DocumentException {
    // 1) Verifică identitatea
    String emailLogged = auth.getName();
    Courier c = courierRepository.findByemail(emailLogged);
    if (c == null || !c.getId().equals(id)) {
      response.sendError(HttpServletResponse.SC_FORBIDDEN);
      return;
    }

    // 2) Generează parola aleatorie pentru PDF
    String userPassword = RandomStringUtils.randomAlphanumeric(8);
    String ownerPassword = "ownerSecret"; // poți schimba după nevoie

    // 3) Configurează răspuns HTTP
    response.setContentType("application/pdf");
    response.setHeader(
      "Content-Disposition",
      "attachment; filename=\"my-packages.pdf\""
    );

    // 4) Generează PDF protejat
    Document doc = new Document(PageSize.A4.rotate());
    PdfWriter writer = PdfWriter.getInstance(doc, response.getOutputStream());
    writer.setEncryption(
      userPassword.getBytes(),
      ownerPassword.getBytes(),
      PdfWriter.ALLOW_PRINTING,
      PdfWriter.ENCRYPTION_AES_128
    );

    doc.open();
    doc.add(new Paragraph("My Assigned Packages"));

    PdfPTable table = new PdfPTable(3);
    table.setWidthPercentage(100);
    table.setWidths(new float[]{3, 5, 4});
    table.addCell("ID");
    table.addCell("Destinație");
    table.addCell("Data update");

    List<com.utcn.scdproiect.pkg.Package> pkgs = packageService.getPackagesForCourier(id);
    for (com.utcn.scdproiect.pkg.Package p : pkgs) {
      table.addCell(p.getId().toString());
      table.addCell(p.getDeliveryAddress());
      table.addCell(p.getCreatedOn().toString());
    }

    doc.add(table);
    doc.close();
  

        // 5) Trimite parola prin email folosind metoda sendEmails() existenta
    String subject = "Parolă PDF pachete OnlyCourier";
    String body = "Salut!" +
                  "PDF-ul tău cu pachetele asignate este protejat cu parola: " +
                  userPassword +
                  "\nEchipa OnlyCourier";
    emailService.sendEmail(emailLogged, body,subject);
  }
}
