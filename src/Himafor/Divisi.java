package Himafor;

public class Divisi {
    private String idDivisi;
    private String namaDivisi;
    private String deskripsi;

    public Divisi() {}

    public Divisi(String idDivisi, String namaDivisi, String deskripsi) {
        this.idDivisi = idDivisi;
        this.namaDivisi = namaDivisi;
        this.deskripsi = deskripsi;
    }

    public String getIdDivisi() { return idDivisi; }
    public void setIdDivisi(String idDivisi) { this.idDivisi = idDivisi; }

    public String getNamaDivisi() { return namaDivisi; }
    public void setNamaDivisi(String namaDivisi) { this.namaDivisi = namaDivisi; }

    public String getDeskripsi() { return deskripsi; }
    public void setDeskripsi(String deskripsi) { this.deskripsi = deskripsi; }
}