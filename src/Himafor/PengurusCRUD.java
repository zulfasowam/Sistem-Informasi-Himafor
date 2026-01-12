package Himafor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PengurusCRUD {
    
    public void simpan(Pengurus p) {
        String sql = "INSERT INTO Pengurus (ID_Pengurus, ID_Anggota, Jabatan, Periode_Jabatan) VALUES (?, ?, ?, ?)";
        try (Connection conn =DBConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getIdPengurus());
            ps.setString(2, p.getIdAnggota());
            ps.setString(3, p.getJabatan());
            ps.setString(4, p.getPeriodeJabatan());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Pengurus p) {
        String sql = "UPDATE Pengurus SET ID_Anggota=?, Jabatan=?, Periode_Jabatan=? WHERE ID_Pengurus=?";
        try (Connection conn = DBConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getIdAnggota());
            ps.setString(2, p.getJabatan());
            ps.setString(3, p.getPeriodeJabatan());
            ps.setString(4, p.getIdPengurus());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void hapus(String id) {
        String sql = "DELETE FROM Pengurus WHERE ID_Pengurus=?";
        try (Connection conn = DBConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Pengurus> getAll() {
        List<Pengurus> list = new ArrayList<>();
        String sql = "SELECT * FROM Pengurus ORDER BY ID_Pengurus ASC";
        try (Connection conn = DBConection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while(rs.next()) {
                list.add(new Pengurus(
                    rs.getString("ID_Pengurus"),
                    rs.getString("ID_Anggota"),
                    rs.getString("Jabatan"),
                    rs.getString("Periode_Jabatan")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}