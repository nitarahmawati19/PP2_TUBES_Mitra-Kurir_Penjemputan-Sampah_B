/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ASUS
 */
public class UlasanPengguna {
    private int idUlasan;
    private String namaPengguna;
    private String ulasan;
    

    public UlasanPengguna(int idUlasan, String namaPengguna, String ulasan) {
        this.idUlasan = idUlasan;
        this.namaPengguna = namaPengguna;
        this.ulasan = ulasan;
    }

    // Getter dan Setter
    public int getIdUlasan() {
        return idUlasan;
    }

    public void setIdUlasan(int idUlasan) {
        this.idUlasan = idUlasan;
    }

    public String getNamaPengguna() {
        return namaPengguna;
    }

    public void setNamaPengguna(String namaPengguna) {
        this.namaPengguna = namaPengguna;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    @Override
    public String toString() {
        return "ID: " + idUlasan + "\n" +
               "Nama: " + namaPengguna + "\n" +
               "Ulasan: " + ulasan + "\n";
    }
}
