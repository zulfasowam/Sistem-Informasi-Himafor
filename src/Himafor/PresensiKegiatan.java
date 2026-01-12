package Himafor;

public class PresensiKegiatan {
    private String idPresensi;
    private String idAnggota;
    private String idKegiatan;
    private String statusHadir;

    public PresensiKegiatan() {}

    public PresensiKegiatan(String idPresensi, String idAnggota, String idKegiatan, String statusHadir) {
        this.idPresensi = idPresensi;
        this.idAnggota = idAnggota;
        this.idKegiatan = idKegiatan;
        this.statusHadir = statusHadir;
    }

    public String getIdPresensi() { return idPresensi; }
    public void setIdPresensi(String idPresensi) { this.idPresensi = idPresensi; }

    public String getIdAnggota() { return idAnggota; }
    public void setIdAnggota(String idAnggota) { this.idAnggota = idAnggota; }

    public String getIdKegiatan() { return idKegiatan; }
    public void setIdKegiatan(String idKegiatan) { this.idKegiatan = idKegiatan; }

    public String getStatusHadir() { return statusHadir; }
    public void setStatusHadir(String statusHadir) { this.statusHadir = statusHadir; }
}