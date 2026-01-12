package Himafor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KegiatanCRUD {
    
    public void simpan(Kegiatan k) {
        String sql = "INSERT INTO Kegiatan VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, k.getIdKegiatan());
            ps.setString(2, k.getNamaKegiatan());
            ps.setDate(3, k.getTanggal());
            ps.setString(4, k.getDeskripsi());
            ps.setString(5, k.getPenanggungJawab());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
 // Tambahkan di dalam class KegiatanDAO
    public List<String> getComboKegiatan() {
        List<String> list = new ArrayList<>();
        String sql = "SELECT ID_Kegiatan, Nama_Kegiatan FROM Kegiatan ORDER BY ID_Kegiatan ASC";
        try (Connection conn = DBConection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while(rs.next()) {
                list.add(rs.getString("ID_Kegiatan") + " - " + rs.getString("Nama_Kegiatan"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public void update(Kegiatan k) {
        String sql = "UPDATE Kegiatan SET Nama_Kegiatan=?, Tanggal=?, Deskripsi=?, Penanggung_Jawab=? WHERE ID_Kegiatan=?";
        try (Connection conn = DBConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, k.getNamaKegiatan());
            ps.setDate(2, k.getTanggal());
            ps.setString(3, k.getDeskripsi());
            ps.setString(4, k.getPenanggungJawab());
            ps.setString(5, k.getIdKegiatan());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void hapus(String id) {
        String sql = "DELETE FROM Kegiatan WHERE ID_Kegiatan=?";
        try (Connection conn = DBConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Kegiatan> getAll() {
        List<Kegiatan> list = new ArrayList<>();
        String sql = "SELECT * FROM Kegiatan ORDER BY ID_Kegiatan ASC";
        try (Connection conn = DBConection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while(rs.next()) {
                list.add(new Kegiatan(
                    rs.getString("ID_Kegiatan"),
                    rs.getString("Nama_Kegiatan"),
                    rs.getDate("Tanggal"),
                    rs.getString("Deskripsi"),
                    rs.getString("Penanggung_Jawab")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}