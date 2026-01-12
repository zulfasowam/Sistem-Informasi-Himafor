package Himafor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnggotaCRUD {
    
    public void simpanAnggota(Anggota a) {
        String sql = "INSERT INTO Anggota VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, a.getIdAnggota());
            ps.setString(2, a.getNama());
            ps.setString(3, a.getNim());
            ps.setString(4, a.getJurusan());
            ps.setString(5, a.getAngkatan());
            ps.setString(6, a.getEmail());
            ps.setString(7, a.getStatusKeaktifan());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

 // Tambahkan di dalam class AnggotaDAO
    public List<String> getComboAnggota() {
        List<String> list = new ArrayList<>();
        String sql = "SELECT ID_Anggota, Nama FROM Anggota ORDER BY ID_Anggota ASC";
        try (Connection conn = DBConection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while(rs.next()) {
                // Format tampilan: "101 - Budi"
                list.add(rs.getString("ID_Anggota") + " - " + rs.getString("Nama"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }
    
    public void updateAnggota(Anggota a) {
        String sql = "UPDATE Anggota SET Nama=?, NIM=?, Jurusan=?, Angkatan=?, Email=?, Status_Keaktifan=? WHERE ID_Anggota=?";
        try (Connection conn = DBConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, a.getNama());
            ps.setString(2, a.getNim());
            ps.setString(3, a.getJurusan());
            ps.setString(4, a.getAngkatan());
            ps.setString(5, a.getEmail());
            ps.setString(6, a.getStatusKeaktifan());
            ps.setString(7, a.getIdAnggota());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // FUNGSI HAPUS (DELETE)
    public void hapusAnggota(String id) {
        String sql = "DELETE FROM Anggota WHERE ID_Anggota=?";
        try (Connection conn = DBConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // FUNGSI LIHAT DATA (READ)
    public List<Anggota> getAllAnggota() {
        List<Anggota> list = new ArrayList<>();
        String sql = "SELECT * FROM Anggota ORDER BY ID_Anggota ASC";
        try (Connection conn = DBConection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while(rs.next()) {
                Anggota a = new Anggota(
                    rs.getString("ID_Anggota"),
                    rs.getString("Nama"),
                    rs.getString("NIM"),
                    rs.getString("Jurusan"),
                    rs.getString("Angkatan"),
                    rs.getString("Email"),
                    rs.getString("Status_Keaktifan")
                );
                list.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}