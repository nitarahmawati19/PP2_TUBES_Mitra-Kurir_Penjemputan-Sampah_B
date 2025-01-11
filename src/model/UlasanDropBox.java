/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ASUS
 */
public class UlasanDropBox {
    private int idUlasan;
    private String lokasiDropBox;
    private String ulasan;

    public UlasanDropBox(int idUlasan, String lokasiDropBox, String ulasan) {
        this.idUlasan = idUlasan;
        this.lokasiDropBox = lokasiDropBox;
        this.ulasan = ulasan;
    }

    // Getter dan Setter
    public int getIdUlasan() {
        return idUlasan;
    }

    public void setIdUlasan(int idUlasan) {
        this.idUlasan = idUlasan;
    }

    public String getLokasiDropBox() {
        return lokasiDropBox;
    }

    public void setLokasiDropBox(String lokasiDropBox) {
        this.lokasiDropBox = lokasiDropBox;
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
               "Lokasi: " + lokasiDropBox + "\n" +
               "Ulasan: " + ulasan + "\n";
    }
}
