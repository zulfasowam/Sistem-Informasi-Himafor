package Himafor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PresensiKegiatanCRUD {
    
    public void simpan(PresensiKegiatan pk) {
        String sql = "INSERT INTO Presensi_Kegiatan VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, pk.getIdPresensi());
            ps.setString(2, pk.getIdAnggota());
            ps.setString(3, pk.getIdKegiatan());
            ps.setString(4, pk.getStatusHadir());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(PresensiKegiatan pk) {
        String sql = "UPDATE Presensi_Kegiatan SET ID_Anggota=?, ID_Kegiatan=?, Status_Hadir=? WHERE ID_Presensi=?";
        try (Connection conn = DBConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, pk.getIdAnggota());
            ps.setString(2, pk.getIdKegiatan());
            ps.setString(3, pk.getStatusHadir());
            ps.setString(4, pk.getIdPresensi());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void hapus(String id) {
        String sql = "DELETE FROM Presensi_Kegiatan WHERE ID_Presensi=?";
        try (Connection conn = DBConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<PresensiKegiatan> getAll() {
        List<PresensiKegiatan> list = new ArrayList<>();
        String sql = "SELECT * FROM Presensi_Kegiatan ORDER BY ID_Presensi ASC";
        try (Connection conn = DBConection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while(rs.next()) {
                list.add(new PresensiKegiatan(
                    rs.getString("ID_Presensi"),
                    rs.getString("ID_Anggota"),
                    rs.getString("ID_Kegiatan"),
                    rs.getString("Status_Hadir")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}