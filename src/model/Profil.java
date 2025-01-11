/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ASUS
 */
public class Profil {
    private String nama;
    private String email;
    private String noTelp;
    private String alamat;

    public Profil(String nama, String email, String noTelp, String alamat) {
        this.nama = nama;
        this.email = email;
        this.noTelp = noTelp;
        this.alamat = alamat;
    }

    // Getter dan Setter
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    @Override
    public String toString() {
        return "Profil{" +
               "nama='" + nama + '\'' +
               ", email='" + email + '\'' +
               ", noTelp='" + noTelp + '\'' +
               ", alamat='" + alamat + '\'' +
               '}';
    }
    
}
