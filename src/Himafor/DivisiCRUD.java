package Himafor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DivisiCRUD {
    
    public void simpan(Divisi d) {
        String sql = "INSERT INTO Divisi VALUES (?, ?, ?)";
        try (Connection conn = DBConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, d.getIdDivisi());
            ps.setString(2, d.getNamaDivisi());
            ps.setString(3, d.getDeskripsi());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 
    public List<String> getComboDivisi() {
        List<String> list = new ArrayList<>();
        String sql = "SELECT ID_Divisi, Nama_Divisi FROM Divisi ORDER BY ID_Divisi ASC";
        try (Connection conn = DBConection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while(rs.next()) {
                list.add(rs.getString("ID_Divisi") + " - " + rs.getString("Nama_Divisi"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }
    
    public void update(Divisi d) {
        String sql = "UPDATE Divisi SET Nama_Divisi=?, Deskripsi=? WHERE ID_Divisi=?";
        try (Connection conn = DBConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, d.getNamaDivisi());
            ps.setString(2, d.getDeskripsi());
            ps.setString(3, d.getIdDivisi());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void hapus(String id) {
        String sql = "DELETE FROM Divisi WHERE ID_Divisi=?";
        try (Connection conn = DBConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Divisi> getAll() {
        List<Divisi> list = new ArrayList<>();
        String sql = "SELECT * FROM Divisi ORDER BY ID_Divisi ASC";
        try (Connection conn = DBConection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while(rs.next()) {
                list.add(new Divisi(
                    rs.getString("ID_Divisi"),
                    rs.getString("Nama_Divisi"),
                    rs.getString("Deskripsi")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}