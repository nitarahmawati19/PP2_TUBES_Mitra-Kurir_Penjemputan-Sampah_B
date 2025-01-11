package view;

import controller.MapController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Keterangan extends JFrame {
    private JLabel namaPemesanLabel;
    private JLabel kategoriBarangLabel;
    private JLabel namaBarangLabel;
    private JLabel alamatLabel;
    private JLabel jarakLabel;
    private JLabel estimasiWaktuLabel;
    private JButton backButton;

    public Keterangan() {
        setTitle("Detail Pemesanan");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(7, 2)); // Ubah layout menjadi 7 baris

        namaPemesanLabel = new JLabel();
        kategoriBarangLabel = new JLabel();
        namaBarangLabel = new JLabel();
        alamatLabel = new JLabel();
        jarakLabel = new JLabel();
        estimasiWaktuLabel = new JLabel();
        backButton = new JButton("Kembali"); // Tambahkan tombol kembali

        add(new JLabel("Nama Pemesan:"));
        add(namaPemesanLabel);
        add(new JLabel("Kategori Barang:"));
        add(kategoriBarangLabel);
        add(new JLabel("Nama Barang:"));
        add(namaBarangLabel);
        add(new JLabel("Alamat:"));
        add(alamatLabel);
        add(new JLabel("Jarak:"));
        add(jarakLabel);
        add(new JLabel("Estimasi Waktu:"));
        add(estimasiWaktuLabel);
        add(backButton); // Tambahkan tombol kembali ke layout

        // Tambahkan ActionListener untuk tombol kembali
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Tutup jendela ini
                dispose();
                // Kembali ke halaman sebelumnya, misalnya buka halaman utama
                MapController mapController = new MapController(); // Pastikan MapController diinisialisasi dengan benar
                mapController.setVisible(true); // Gantilah dengan halaman yang sesuai
            }
        });

        loadData();
    }

    private void loadData() {
        try {
            // Debugging output to ensure the method is called
            System.out.println("Memulai proses load data...");

            // Load and register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver JDBC berhasil didaftarkan");

            // Establish connection to the database
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ewaste_management", "root", "");
            System.out.println("Koneksi ke database berhasil");

            // Create a statement
            Statement stmt = conn.createStatement();
            String query = "SELECT nama_pemesan, kategori_barang, nama_barang, alamat, jarak, estimasi_waktu FROM pemesanan WHERE id = 1";
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("Query berhasil dijalankan");

            // Check if there is a result
            if (rs.next()) {
                namaPemesanLabel.setText(rs.getString("nama_pemesan"));
                kategoriBarangLabel.setText(rs.getString("kategori_barang"));
                namaBarangLabel.setText(rs.getString("nama_barang"));
                alamatLabel.setText(rs.getString("alamat"));
                jarakLabel.setText(rs.getString("jarak"));
                estimasiWaktuLabel.setText(rs.getString("estimasi_waktu"));
                System.out.println("Data berhasil diambil dan ditampilkan");
            } else {
                System.out.println("Data tidak ditemukan");
            }

            // Close resources
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Keterangan().setVisible(true));
    }
}