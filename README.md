# Sistem Presensi Organisasi HIMAFOR 🎓

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Oracle](https://img.shields.io/badge/Oracle-F80000?style=for-the-badge&logo=oracle&logoColor=white)

Aplikasi desktop untuk manajemen data organisasi mahasiswa (HIMAFOR). Proyek ini dibangun menggunakan **Java GUI (Swing)** dan terintegrasi dengan **Oracle Database** menggunakan driver JDBC.

## 📸 Screenshots
*(Nanti kamu bisa upload foto screenshot aplikasimu di sini, atau hapus bagian ini jika belum ada)*

## ✨ Fitur Utama
* **Manajemen Data (CRUD):** Simpan, Lihat, Edit, dan Hapus data.
* **Relational Database:** Mengelola 6 tabel terelasi (Anggota, Pengurus, Divisi, Kegiatan, dll).
* **User Friendly UI:**
    * **Auto-Populate ComboBox:** Input ID otomatis dengan memilih nama (mencegah kesalahan input ID).
    * **Date Picker:** Input tanggal menggunakan JSpinner kalender.
    * **Tabbed Interface:** Navigasi antar menu yang mudah dalam satu window.

## 🛠️ Teknologi yang Digunakan
* **Bahasa:** Java (JDK 8+)
* **Database:** Oracle Database Express Edition (XE)
* **Driver:** Oracle JDBC Driver (`ojdbc8.jar`)
* **IDE:** Eclipse / IntelliJ IDEA / NetBeans

## 🚀 Cara Menjalankan (How to Run)
1.  **Clone Repository ini:**
    ```bash
    git clone [https://github.com/username-kamu/Nama-Repository.git](https://github.com/username-kamu/Nama-Repository.git)
    ```
2.  **Setup Database:**
    * Buka Oracle SQL Developer.
    * Buat User baru (jika perlu).
    * Jalankan script SQL yang ada di folder `database/script.sql` (Pastikan kamu upload script SQL-mu juga).
3.  **Konfigurasi Koneksi:**
    * Buka file `src/Himafor/KoneksiDB.java`.
    * Sesuaikan `username` dan `password` database Oracle kamu.
4.  **Run Aplikasi:**
    * Jalankan file `Main.java`.

## 👤 Author
**Zulfa Sowam**

**Adhwa Ainun NIssa**
* Mahasiswa Informatika - Universitas Muhammadiyah Semarang (UNIMUS)

---
*Proyek ini dibuat untuk memenuhi tugas Praktikum Struktur Data.*
