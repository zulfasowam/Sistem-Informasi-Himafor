package Himafor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConection {
    
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    

    private static final String USER = "SYSTEM";
    private static final String PASSWORD = "root"; 
    
    
    static {
        try {
         
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("Driver Oracle berhasil ditemukan.");
        } catch (ClassNotFoundException e) {
            System.err.println("Error: Driver Oracle (ojdbc.jar) tidak ditemukan di Library!");
            e.printStackTrace();
        }
    }
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

   
    public static void main(String[] args) {
        System.out.println("Sedang mencoba koneksi ke Oracle...");
        
        try {
            Connection conn = getConnection();
            if (conn != null) {
                System.out.println("----------------------------------------");
                System.out.println("SUKSES: Koneksi ke Oracle Database berhasil!");
                System.out.println("----------------------------------------");
              
                conn.close();
            }
        } catch (SQLException e) {
            System.err.println("GAGAL: Tidak bisa terhubung ke database.");
            System.err.println("Cek: Username, Password, atau pastikan Oracle Service berjalan.");
            e.printStackTrace(); 
        }
    }
}