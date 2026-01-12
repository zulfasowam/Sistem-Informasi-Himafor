package Himafor;

import java.sql.Date;

public class Kegiatan {
    private String idKegiatan;
    private String namaKegiatan;
    private Date tanggal; // Menggunakan java.sql.Date agar cocok dengan Oracle DATE
    private String deskripsi;
    private String penanggungJawab;

    public Kegiatan() {}

    public Kegiatan(String id, String nama, Date tgl, String desk, String pj) {
        this.idKegiatan = id;
        this.namaKegiatan = nama;
        this.tanggal = tgl;
        this.deskripsi = desk;
        this.penanggungJawab = pj;
    }

    // Getters Setters
    public String getIdKegiatan() { return idKegiatan; }
    public void setIdKegiatan(String idKegiatan) { this.idKegiatan = idKegiatan; }

    public String getNamaKegiatan() { return namaKegiatan; }
    public void setNamaKegiatan(String namaKegiatan) { this.namaKegiatan = namaKegiatan; }

    public Date getTanggal() { return tanggal; }
    public void setTanggal(Date tanggal) { this.tanggal = tanggal; }

    public String getDeskripsi() { return deskripsi; }
    public void setDeskripsi(String deskripsi) { this.deskripsi = deskripsi; }

    public String getPenanggungJawab() { return penanggungJawab; }
    public void setPenanggungJawab(String penanggungJawab) { this.penanggungJawab = penanggungJawab; }
}