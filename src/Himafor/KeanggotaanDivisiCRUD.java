package Himafor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KeanggotaanDivisiCRUD {
    
    
    
    public void simpan(KeanggotaanDivisi kd) {
        String sql = "INSERT INTO Keanggotaan_Divisi VALUES (?, ?, ?)";
        try (Connection conn = DBConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, kd.getIdKeanggotaan());
            ps.setString(2, kd.getIdDivisi());
            ps.setString(3, kd.getPeriode());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(KeanggotaanDivisi kd) {
        String sql = "UPDATE Keanggotaan_Divisi SET ID_Divisi=?, Periode=? WHERE ID_Keanggotaan=?";
        try (Connection conn = DBConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, kd.getIdDivisi());
            ps.setString(2, kd.getPeriode());
            ps.setString(3, kd.getIdKeanggotaan());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void hapus(String id) {
        String sql = "DELETE FROM Keanggotaan_Divisi WHERE ID_Keanggotaan=?";
        try (Connection conn = DBConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<KeanggotaanDivisi> getAll() {
        List<KeanggotaanDivisi> list = new ArrayList<>();
        String sql = "SELECT * FROM Keanggotaan_Divisi ORDER BY ID_Keanggotaan ASC";
        try (Connection conn = DBConection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while(rs.next()) {
                list.add(new KeanggotaanDivisi(
                    rs.getString("ID_Keanggotaan"),
                    rs.getString("ID_Divisi"),
                    rs.getString("Periode")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}