
CREATE TABLE Anggota (
    ID_Anggota VARCHAR2(20) PRIMARY KEY,
    Nama VARCHAR2(100),
    NIM VARCHAR2(20),
    Jurusan VARCHAR2(50),
    Angkatan VARCHAR2(4),
    Email VARCHAR2(100),
    Status_Keaktifan VARCHAR2(20)
);

CREATE TABLE Pengurus (
    ID_Pengurus VARCHAR2(20) PRIMARY KEY,
    ID_Anggota VARCHAR2(20),
    Jabatan VARCHAR2(50),
    Periode_Jabatan VARCHAR2(20)
    -- Jika error foreign key, hapus baris FOREIGN KEY di bawah ini dulu
);


CREATE TABLE Kegiatan (
    ID_Kegiatan VARCHAR2(20) PRIMARY KEY,
    Nama_Kegiatan VARCHAR2(100),
    Tanggal DATE,
    Deskripsi VARCHAR2(255),
    Penanggung_Jawab VARCHAR2(100)
);


CREATE TABLE Divisi (
    ID_Divisi VARCHAR2(20) PRIMARY KEY,
    Nama_Divisi VARCHAR2(50),
    Deskripsi VARCHAR2(255)
);


CREATE TABLE Keanggotaan_Divisi (
    ID_Keanggotaan VARCHAR2(20) PRIMARY KEY,
    ID_Divisi VARCHAR2(20),
    Periode VARCHAR2(20)
);

-- 6. Tabel Presensi Kegiatan
CREATE TABLE Presensi_Kegiatan (
    ID_Presensi VARCHAR2(20) PRIMARY KEY,
    ID_Anggota VARCHAR2(20),
    ID_Kegiatan VARCHAR2(20),
    Status_Hadir VARCHAR2(20)
);


COMMIT;