package view;

import model.PickupHistory;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class PickupHistoryPdf {
    public void exportPdf(List<PickupHistory> histories) {
        Document document = new Document(PageSize.A4);
        
        try {
            PdfWriter.getInstance(document, new FileOutputStream(System.getProperty("user.dir") + File.separator + "PickupHistoryReport.pdf"));
            document.open();
            
            float[] columnWidths = {20f, 20f, 20f, 20f, 20f};
            PdfPTable table = new PdfPTable(columnWidths);
            table.setWidthPercentage(100);
            
            addTableHeader(table);
            addRows(table, histories);
            
            document.add(table);
            
            // Menampilkan pesan notifikasi setelah PDF berhasil dibuat
            JOptionPane.showMessageDialog(null, "PDF berhasil diunduh!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat mengunduh PDF: " + e.getMessage(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
        } finally {
            document.close();
        }
    }

    private void addTableHeader(PdfPTable table) {
        Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        String[] headers = {"Tanggal", "Pemesan", "Barang", "Alamat", "Status"};
        
        for (String header : headers) {
            PdfPCell headerCell = new PdfPCell(new Phrase(header, headFont));
            headerCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            table.addCell(headerCell);
        }
    }
    
    private void addRows(PdfPTable table, List<PickupHistory> histories) {
        Font bodyFont = FontFactory.getFont(FontFactory.HELVETICA);
        
        for (PickupHistory history : histories) {
            table.addCell(new PdfPCell(new Phrase(history.getDate(), bodyFont)));
            table.addCell(new PdfPCell(new Phrase(history.getPemesan(), bodyFont)));
            table.addCell(new PdfPCell(new Phrase(history.getBarang(), bodyFont)));
            table.addCell(new PdfPCell(new Phrase(history.getAddress(), bodyFont)));
            table.addCell(new PdfPCell(new Phrase(history.getStatus(), bodyFont)));
        }
    }
}