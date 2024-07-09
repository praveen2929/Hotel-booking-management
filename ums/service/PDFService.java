//package com.ums.service;
//
//import com.itextpdf.text.BaseColor;
//import com.itextpdf.text.Chunk;
//import com.itextpdf.text.Font;
//import com.itextpdf.text.FontFactory;
//import com.itextpdf.text.pdf.PdfWriter;
//import org.springframework.stereotype.Service;
//
//import javax.swing.text.Document;
//import java.io.FileOutputStream;
//
//@Service
//public class PDFService {
//    public void generateBookingDetailsPdf(){
//        try {
//            Document document = new Document();
//            PdfWriter.getInstance((com.itextpdf.text.Document) document, new FileOutputStream("D://nov_file//iTextHelloWorld.pdf"));
//
//            document.open();
//            Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
//            Chunk chunk = new Chunk("Hello World", font);
//
//            document.add(chunk);
//            document.close();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//}



//package com.ums.service;
//
//import com.itextpdf.text.BaseColor;
//import com.itextpdf.text.Chunk;
//import com.itextpdf.text.Document;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.Font;
//import com.itextpdf.text.FontFactory;
//import com.itextpdf.text.pdf.PdfWriter;
//import com.ums.entity.Booking;
//import com.ums.repository.BookingRepository;
//import org.springframework.stereotype.Service;
//
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//
//@Service
//public class PDFService {
//    public void generateBookingDetailsPdf(
//            Booking booking
//    ) {
//        try {
//            Document document = new Document();
//            PdfWriter.getInstance(document, new FileOutputStream("D://airbnb-booking//Booking-confirmation"+booking.getId()+".pdf"));
//
//            document.open();
//            Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
//            Chunk chunk = new Chunk("Hello World", font);
//
//            document.add(chunk);
//            document.close();
//        } catch (DocumentException | FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//}

//
//package com.ums.service;
//
//import com.itextpdf.text.*;
//import com.itextpdf.text.pdf.PdfPCell;
//import com.itextpdf.text.pdf.PdfPTable;
//import com.itextpdf.text.pdf.PdfWriter;
//import com.ums.entity.Booking;
//import org.springframework.stereotype.Service;
//
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//
//@Service
//public class PDFService {
//    public void generateBookingDetailsPdf(Booking booking) {
//        try {
//            Document document = new Document();
//            PdfWriter.getInstance(document, new FileOutputStream("D://airbnb-booking//Booking-confirmation" + booking.getId() + ".pdf"));
//
//            document.open();
//
//            // Add title
//            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLACK);
//            Paragraph title = new Paragraph("Booking Confirmation", titleFont);
//            title.setAlignment(Paragraph.ALIGN_CENTER);
//            document.add(title);
//            document.add(Chunk.NEWLINE);
//
//            // Create table with 2 columns
//            PdfPTable table = new PdfPTable(2);
//            table.setWidthPercentage(100);
//            table.setSpacingBefore(10f);
//            table.setSpacingAfter(10f);
//
//            // Set table header
//            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
//            PdfPCell hcell;
//            hcell = new PdfPCell(new Phrase("Field", headFont));
//            hcell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
//            table.addCell(hcell);
//
//            hcell = new PdfPCell(new Phrase("Value", headFont));
//            hcell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
//            table.addCell(hcell);
//
//            // Add booking details
//            addTableRow(table, "Guest Name", booking.getGuestName());
//            addTableRow(table, "Total Nights", booking.getTotalNights().toString());
//            addTableRow(table, "Total Price", booking.getTotalPrice().toString());
//            addTableRow(table, "Booking Date", booking.getBookingDate().toString());
//            addTableRow(table, "Check-in Time", booking.getCheckInTime().toString());
//            addTableRow(table, "Property", booking.getProperty().getName());  // Assuming Property has a getName() method
//            addTableRow(table, "User", booking.getAppUser().getUsername());   // Assuming AppUser has a getUsername() method
//
//            document.add(table);
//            document.close();
//        } catch (DocumentException | FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void addTableRow(PdfPTable table, String field, String value) {
//        Font font = FontFactory.getFont(FontFactory.HELVETICA);
//        PdfPCell cell;
//
//        cell = new PdfPCell(new Phrase(field, font));
//        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
//        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
//        table.addCell(cell);
//
//        cell = new PdfPCell(new Phrase(value, font));
//        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
//        cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
//        table.addCell(cell);
//    }
//}
//
//


package com.ums.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.ums.entity.Booking;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

@Service
public class PDFService {
    public String generateBookingDetailsPdf(Booking booking) {
        try {
            // Ensure the directory exists
            String filePath = "D://airbnb-booking//Booking-confirmation" + booking.getId() + ".pdf";
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);

            Document document = new Document();
            PdfWriter.getInstance(document, fileOutputStream);

            document.open();

            // Add title
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLACK);
            Paragraph title = new Paragraph("Booking Confirmation", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(Chunk.NEWLINE);

            // Create table with 2 columns
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            // Set table header
            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            PdfPCell hcell;
            hcell = new PdfPCell(new Phrase("Field", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Value", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            // Add booking details
            addTableRow(table, "Guest Name", booking.getGuestName());
            addTableRow(table, "Total Nights", booking.getTotalNights() != null ? booking.getTotalNights().toString() : "N/A");
            addTableRow(table, "Total Price", booking.getTotalPrice() != null ? booking.getTotalPrice().toString() : "N/A");
            addTableRow(table, "Booking Date", booking.getBookingDate() != null ? booking.getBookingDate().toString() : "N/A");
            addTableRow(table, "Check-in Time", booking.getCheckInTime() != null ? booking.getCheckInTime().toString() : "N/A");

            if (booking.getProperty() != null) {
                addTableRow(table, "Property", booking.getProperty().getPropertyName());
            } else {
                addTableRow(table, "Property", "N/A");
            }

            if (booking.getAppUser() != null) {
                addTableRow(table, "User", booking.getAppUser().getUsername());
            } else {
                addTableRow(table, "User", "N/A");
            }

            document.add(table);
            document.close();
            return "D://airbnb-booking//Booking-confirmation" + booking.getId() + ".pdf";
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void addTableRow(PdfPTable table, String field, String value) {
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        PdfPCell cell;

        cell = new PdfPCell(new Phrase(field, font));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(value, font));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
    }
}
