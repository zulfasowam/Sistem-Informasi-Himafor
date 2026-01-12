package Himafor;

public class Pengurus {
    private String idPengurus;
    private String idAnggota;
    private String jabatan;
    private String periodeJabatan;

    public Pengurus() {}

    public Pengurus(String idPengurus, String idAnggota, String jabatan, String periodeJabatan) {
        this.idPengurus = idPengurus;
        this.idAnggota = idAnggota;
        this.jabatan = jabatan;
        this.periodeJabatan = periodeJabatan;
    }

    public String getIdPengurus() { return idPengurus; }
    public void setIdPengurus(String idPengurus) { this.idPengurus = idPengurus; }

    public String getIdAnggota() { return idAnggota; }
    public void setIdAnggota(String idAnggota) { this.idAnggota = idAnggota; }

    public String getJabatan() { return jabatan; }
    public void setJabatan(String jabatan) { this.jabatan = jabatan; }

    public String getPeriodeJabatan() { return periodeJabatan; }
    public void setPeriodeJabatan(String periodeJabatan) { this.periodeJabatan = periodeJabatan; }
}