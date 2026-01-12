package Himafor;

public class KeanggotaanDivisi {
    private String idKeanggotaan;
    private String idDivisi;
    private String periode;

    public KeanggotaanDivisi() {}

    public KeanggotaanDivisi(String idKeanggotaan, String idDivisi, String periode) {
        this.idKeanggotaan = idKeanggotaan;
        this.idDivisi = idDivisi;
        this.periode = periode;
    }

    public String getIdKeanggotaan() { return idKeanggotaan; }
    public void setIdKeanggotaan(String idKeanggotaan) { this.idKeanggotaan = idKeanggotaan; }

    public String getIdDivisi() { return idDivisi; }
    public void setIdDivisi(String idDivisi) { this.idDivisi = idDivisi; }

    public String getPeriode() { return periode; }
    public void setPeriode(String periode) { this.periode = periode; }
}