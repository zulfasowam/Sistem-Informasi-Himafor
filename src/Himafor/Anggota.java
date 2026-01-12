package Himafor;

public class Anggota {
    private String idAnggota;
    private String nama;
    private String nim;
    private String jurusan;
    private String angkatan;
    private String email;
    private String statusKeaktifan;

    // Constructor Kosong
    public Anggota() {}

    // Constructor Isi
    public Anggota(String id, String nm, String nim, String jur, String ang, String em, String stat) {
        this.idAnggota = id;
        this.nama = nm;
        this.nim = nim;
        this.jurusan = jur;
        this.angkatan = ang;
        this.email = em;
        this.statusKeaktifan = stat;
    }

    // Getter Setter (Penting untuk akses data)
    public String getIdAnggota() { return idAnggota; }
    public void setIdAnggota(String idAnggota) { this.idAnggota = idAnggota; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public String getNim() { return nim; }
    public void setNim(String nim) { this.nim = nim; }

    public String getJurusan() { return jurusan; }
    public void setJurusan(String jurusan) { this.jurusan = jurusan; }

    public String getAngkatan() { return angkatan; }
    public void setAngkatan(String angkatan) { this.angkatan = angkatan; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getStatusKeaktifan() { return statusKeaktifan; }
    public void setStatusKeaktifan(String statusKeaktifan) { this.statusKeaktifan = statusKeaktifan; }
}