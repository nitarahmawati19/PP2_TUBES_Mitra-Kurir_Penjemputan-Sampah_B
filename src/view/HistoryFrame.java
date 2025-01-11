package view;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.util.List;
import model.PickupHistory;

public class HistoryFrame extends JFrame {

    private JButton backButton;
    private JLabel activityLabel;
    private JButton dashboardButton;
    private JButton historyButton;
    private JPanel mainPanel;
    private JPanel dashboardPanel;
    private JPanel historyPanel;
    private JTable table;
    private JButton refreshButton;
    private JButton downloadPdfButton;
    private JComboBox<String> categoryDropdown;

    public HistoryFrame() {
        initializeComponents();
    }
    
    public JButton getDownloadPdfButton() {
        return downloadPdfButton;
    }


    private void initializeComponents() {
        setTitle("Riwayat Penjemputan Sampah Elektronik");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Top panel with back button and activity label
        JPanel topPanel = new JPanel(new BorderLayout());
        backButton = new JButton("Back");
        activityLabel = new JLabel("Aktivitas", SwingConstants.CENTER);
        activityLabel.setFont(new Font("Arial", Font.BOLD, 18));
        topPanel.add(backButton, BorderLayout.WEST);
        topPanel.add(activityLabel, BorderLayout.CENTER);

        // Navigation buttons
        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        dashboardButton = new JButton("Dashboard");
        historyButton = new JButton("Riwayat");
        navPanel.add(dashboardButton);
        navPanel.add(historyButton);

        // Main panel with card layout
        mainPanel = new JPanel(new CardLayout());

        // Dashboard panel
        dashboardPanel = new JPanel(null);
        JLabel profileLabel = new JLabel(new ImageIcon("path/to/profile/image")); // Replace with actual image path
        profileLabel.setBounds(50, 30, 100, 100);
        dashboardPanel.add(profileLabel);

        JLabel pointsLabel = new JLabel("Poin Anda:");
        pointsLabel.setBounds(170, 30, 100, 30);
        dashboardPanel.add(pointsLabel);

        JLabel pointsValueLabel = new JLabel("1000"); // Replace with actual points value
        pointsValueLabel.setBounds(170, 60, 100, 30);
        dashboardPanel.add(pointsValueLabel);

        JLabel totalPickupLabel = new JLabel("Total Penjemputan:");
        totalPickupLabel.setBounds(170, 100, 150, 30);
        dashboardPanel.add(totalPickupLabel);

        // Placeholder for pie chart
        JLabel pieChartLabel = new JLabel(new ImageIcon("path/to/pie/chart/image")); // Replace with actual image path
        pieChartLabel.setBounds(170, 130, 200, 200);
        dashboardPanel.add(pieChartLabel);

        // History panel
        historyPanel = new JPanel(new BorderLayout());
        JPanel historyTopPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        String[] categories = {"Peralatan Pertukaran Suhu", "Layar", "Lampu", "Peralatan Besar", "Peralatan Kecil", "Peralatan IT"};
        categoryDropdown = new JComboBox<>(categories);
        historyTopPanel.add(categoryDropdown);
        refreshButton = new JButton("Refresh");
        historyTopPanel.add(refreshButton);

        downloadPdfButton = new JButton("Unduh PDF");
        historyTopPanel.add(downloadPdfButton);

        historyPanel.add(historyTopPanel, BorderLayout.NORTH);

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        historyPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(dashboardPanel, "Dashboard");
        mainPanel.add(historyPanel, "History");

        // Set initial view to Dashboard
        CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
        cardLayout.show(mainPanel, "Dashboard");

        // Add action listeners to navigation buttons
        dashboardButton.addActionListener(e -> cardLayout.show(mainPanel, "Dashboard"));
        historyButton.addActionListener(e -> cardLayout.show(mainPanel, "History"));

        // Add action listener to download PDF button
        downloadPdfButton.addActionListener(e -> downloadPdfReport());

        // Add components to the frame
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(topPanel, BorderLayout.NORTH);
        getContentPane().add(navPanel, BorderLayout.CENTER);
        getContentPane().add(mainPanel, BorderLayout.SOUTH);
    }

    public void setTableData(List<PickupHistory> historyList) {
        String[] columnNames = {"Tanggal", "Pemesan", "Barang", "Alamat", "Status"};
        Object[][] data = new Object[historyList.size()][columnNames.length];

        for (int i = 0; i < historyList.size(); i++) {
            PickupHistory history = historyList.get(i);
            data[i][0] = history.getDate();
            data[i][1] = history.getPemesan();
            data[i][2] = history.getBarang();
            data[i][3] = history.getAddress();
            data[i][4] = history.getStatus();
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        table.setModel(model);
    }

    private void downloadPdfReport() {
        try (PDDocument document = new PDDocument()) {

            // Create a new page and add it to the document
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            // Create a content stream to write content to the page
            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {

                // Add title to the document
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 18);
                contentStream.newLineAtOffset(100, 750);
                contentStream.showText("Pickup History Report");
                contentStream.endText();

                // Create table header
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                float margin = 50;
                float yStart = 700;
                float yPosition = yStart;

                // Draw table header
                contentStream.beginText();
                contentStream.newLineAtOffset(margin, yPosition);
                for (int i = 0; i < table.getColumnCount(); i++) {
                    contentStream.showText(table.getColumnName(i) + "    ");
                }
                contentStream.endText();
                yPosition -= 20;

                // Draw table rows
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                for (int rows = 0; rows < table.getRowCount(); rows++) {
                    contentStream.beginText();
                    contentStream.newLineAtOffset(margin, yPosition);
                    for (int cols = 0; cols < table.getColumnCount(); cols++) {
                        contentStream.showText(table.getModel().getValueAt(rows, cols).toString() + "    ");
                    }
                    contentStream.endText();
                    yPosition -= 20;

                    // Check if we need a new page
                    if (yPosition <= 50) { // 50 is bottom margin
                        page = new PDPage(PDRectangle.A4);
                        document.addPage(page);
                        yPosition = 700; // Reset yPosition for new page
                        contentStream.close();
                        contentStream = new PDPageContentStream(document, page);
                    }
                }
            }

            // Save the document
            document.save("PickupHistoryReport.pdf");
            JOptionPane.showMessageDialog(this, "PDF Report generated successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error generating PDF Report: " + e.getMessage());
        }
    }

    public JButton getRefreshButton() {
        return refreshButton;
    }

    public JComboBox<String> getCategoryDropdown() {
        return categoryDropdown;
    }
}