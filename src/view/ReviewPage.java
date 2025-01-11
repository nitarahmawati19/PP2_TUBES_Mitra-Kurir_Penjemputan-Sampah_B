/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author ASUS
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import model.Review;

public class ReviewPage extends JFrame {
    private JTextField txtNamaPengguna;
    private JTextArea txtReview;
    private JTable tblReview;
    private DefaultTableModel tableModel;
    private List<Review> reviewList;

    public ReviewPage() {
        // Inisialisasi List untuk menyimpan data review
        reviewList = new ArrayList<>();

        // Komponen GUI
        setTitle("Halaman Review");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblNamaPengguna = new JLabel("Nama Pengguna:");
        lblNamaPengguna.setBounds(20, 20, 120, 25);
        add(lblNamaPengguna);

        txtNamaPengguna = new JTextField();
        txtNamaPengguna.setBounds(140, 20, 200, 25);
        add(txtNamaPengguna);

        JLabel lblReview = new JLabel("Review:");
        lblReview.setBounds(20, 60, 120, 25);
        add(lblReview);

        txtReview = new JTextArea();
        JScrollPane scrollReview = new JScrollPane(txtReview);
        scrollReview.setBounds(140, 60, 200, 80);
        add(scrollReview);

        JButton btnTambah = new JButton("Tambah");
        btnTambah.setBounds(400, 20, 100, 25);
        add(btnTambah);

        JButton btnEdit = new JButton("Edit");
        btnEdit.setBounds(400, 60, 100, 25);
        add(btnEdit);

        JButton btnHapus = new JButton("Hapus");
        btnHapus.setBounds(400, 100, 100, 25);
        add(btnHapus);

        // Tabel untuk menampilkan data review
        String[] columnNames = {"Nama Pengguna", "Review"};
        tableModel = new DefaultTableModel(columnNames, 0);
        tblReview = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tblReview);
        scrollPane.setBounds(20, 200, 650, 200);
        add(scrollPane);

        // Tombol Tambah
        btnTambah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tambahReview();
            }
        });

        // Tombol Edit
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editReview();
            }
        });

        // Tombol Hapus
        btnHapus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hapusReview();
            }
        });
    }

    private void tambahReview() {
        String namaPengguna = txtNamaPengguna.getText();
        String review = txtReview.getText();

        if (namaPengguna.isEmpty() || review.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua field harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Tambahkan data ke tabel
        tableModel.addRow(new Object[]{namaPengguna, review});

        // Simpan data ke List
        reviewList.add(new Review(namaPengguna, review));

        // Bersihkan input
        clearFields();
    }

    private void editReview() {
        int selectedRow = tblReview.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih review yang ingin diubah!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String namaPengguna = txtNamaPengguna.getText();
        String review = txtReview.getText();

        if (namaPengguna.isEmpty() || review.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua field harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Update data di tabel
        tableModel.setValueAt(namaPengguna, selectedRow, 0);
        tableModel.setValueAt(review, selectedRow, 1);

        // Update data di List
        reviewList.set(selectedRow, new Review(namaPengguna, review));

        // Bersihkan input
        clearFields();
    }

    private void hapusReview() {
        int selectedRow = tblReview.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih review yang ingin dihapus!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Hapus data dari tabel
        tableModel.removeRow(selectedRow);

        // Hapus data dari List
        reviewList.remove(selectedRow);

        JOptionPane.showMessageDialog(this, "Review berhasil dihapus!");
    }

    private void clearFields() {
        txtNamaPengguna.setText("");
        txtReview.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ReviewPage reviewPage = new ReviewPage();
            reviewPage.setVisible(true);
        });
    }
}

