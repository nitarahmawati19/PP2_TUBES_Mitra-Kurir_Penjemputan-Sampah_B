package model;

public class PickupHistory {
    private String date;
    private String pemesan;
    private String barang;
    private String address;
    private String status;

    // Getters and setters

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPemesan() {
        return pemesan;
    }

    public void setPemesan(String pemesan) {
        this.pemesan = pemesan;
    }

    public String getBarang() {
        return barang;
    }

    public void setBarang(String barang) {
        this.barang = barang;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}