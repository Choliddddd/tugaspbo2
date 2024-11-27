/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg2210020169_uji;
import java.sql.PreparedStatement;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
/**
 *
 * @author ASUS
 */
public class dbCRUD {
    String jdbcUrl = "jdbc:mysql://localhost:3306/2210020169_periklanan";
    String username = "root";
    String password = "";
    Connection koneksi;    
    
        public dbCRUD (){
        try(Connection Koneksi = DriverManager.getConnection(jdbcUrl, username, password)) {
            Driver mysqlDriver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(mysqlDriver);
            
            System.out.println("input berhasil men!");
        } catch (SQLException error) {
            System.err.println(error.toString());
        }
    }
        
public Connection getKoneksi () throws SQLException{
        try {
            Driver mysqlDriver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(mysqlDriver);
        } catch (SQLException e) {
            System.err.println(e.toString());
        }
        return DriverManager.getConnection(this.jdbcUrl, this.username, this.password);
    }
        
public boolean duplicateKey(String table, String primaryKey, String value) {
    boolean hasil = false;
    String query = "SELECT * FROM " + table + " WHERE " + primaryKey + " = ?";
    try (Connection koneksi = getKoneksi(); 
         PreparedStatement stmt = koneksi.prepareStatement(query)) {
        stmt.setString(1, value);
        ResultSet rs = stmt.executeQuery();
        hasil = rs.next();
    } catch (SQLException e) {
        System.err.println("Error checking duplicate key: " + e.toString());
    }
    return hasil;
    }

                                //SIMPAN STATEMENT

    public void SimpanKARYAWANStatement(String id_karyawan,String nama_karyawan,String alamat,String no_telp,String posisi) {
        try{
            if (duplicateKey("karyawan", "id_karyawan", id_karyawan)) {
                JOptionPane.showMessageDialog(null,"ID KARYAWAN SUDAH TERINPUT");
            } else {
                String SQL = "INSERT INTO karyawan (id_karyawan, nama_karyawan, alamat, no_telp, posisi) VALUE('"+ id_karyawan + "','" + nama_karyawan + "','" + alamat + "','" + no_telp + "','" + posisi + "')";
                Statement perintah = getKoneksi().createStatement();
                perintah.executeUpdate(SQL);
                perintah.close();
                getKoneksi().close();
                JOptionPane.showMessageDialog(null,"DATA BERHASIL DISIMPAN");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }    
    
    public void SimpanREKLAMEStatement(String kd_reklame,String nama_reklame,String satuan, String harga) {
        try{
            if (duplicateKey("reklame", "kd_reklame", kd_reklame)) {
                JOptionPane.showMessageDialog(null,"KODE REKLAME SUDAH TERINPUT");
            } else {
                String SQL = "INSERT INTO reklame (kd_reklame, nama_reklame, satuan, harga) VALUES('"+ kd_reklame + "','" + nama_reklame + "','" + satuan + "','" + harga + "')";
                Statement perintah = getKoneksi().createStatement();
                perintah.executeUpdate(SQL);
                perintah.close();
                getKoneksi().close();
                JOptionPane.showMessageDialog(null,"DATA BERHASIL DISIMPAN");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
    public void SimpanPELANGGANStatement(String id_pelanggan,String nama,String no_telp,String nama_perusahaan,String alamat) {
        try{
            if (duplicateKey("pelanggan", "id_pelanggan", id_pelanggan)) {
                JOptionPane.showMessageDialog(null,"ID PELANGGAN SUDAH TERINPUT");
            } else {
                String SQL = "INSERT INTO pelanggan (id_pelanggan, nama, no_telp, nama_perusahaan, alamat) VALUE('"+ id_pelanggan + "','" + nama + "','" + no_telp + "','" + nama_perusahaan + "','" + alamat + "')";
                Statement perintah = getKoneksi().createStatement();
                perintah.executeUpdate(SQL);
                perintah.close();
                getKoneksi().close();
                JOptionPane.showMessageDialog(null,"DATA BERHASIL DISIMPAN");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
        
        
                                //SIMPAN PREPARED STATEMENT
    public void SimpanKARYAWANPreparedstmt(String id_karyawan,String nama_karyawan,String alamat,String no_telp,String posisi) {
    try {
        if (duplicateKey("karyawan", "id_karyawan", id_karyawan)) {
            JOptionPane.showMessageDialog(null,"ID Karyawan SUDAH TERINPUT");
        } else {
            String SQL = "INSERT INTO karyawan (id_karyawan, nama_karyawan, alamat, no_telp, posisi) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement simpan = getKoneksi().prepareStatement(SQL);
            simpan.setString(1, id_karyawan);
            simpan.setString(2, nama_karyawan);
            simpan.setString(3, alamat);
            simpan.setString(4, no_telp);
            simpan.setString(5, posisi);
            simpan.executeUpdate();
            System.out.println("Data Berhasil Disimpan");
            simpan.close();
            getKoneksi().close();
        }
    } catch (Exception e) {
        System.out.println(e.toString());
    }
   }        
    
    public void SimpanREKLAMEPreparedstmt(String kd_reklame,String nama_reklame,String satuan,String harga) {
    try {
        if (duplicateKey("reklame", "kd_reklame", kd_reklame)) {
            JOptionPane.showMessageDialog(null,"KD REKLAME SUDAH TERINPUT");
        } else {
            String SQL = "INSERT INTO reklame (kd_reklame, nama_reklame, satuan, harga) VALUES (?, ?, ?, ?)";
            PreparedStatement simpan = getKoneksi().prepareStatement(SQL);
            simpan.setString(1, kd_reklame);
            simpan.setString(2, nama_reklame);
            simpan.setString(3, satuan);
            simpan.setString(4, harga);
            simpan.executeUpdate();
            System.out.println("Data Berhasil Disimpan");
            simpan.close();
            getKoneksi().close();
        }
    } catch (Exception e) {
        System.out.println(e.toString());
        }
   }
    
    public void SimpanPELANGGANPreparedstmt(String id_pelanggan,String nama,String no_telp,String nama_perusahaan,String alamat) {
    try {
        if (duplicateKey("pelanggan", "id_pelanggan", id_pelanggan)) {
            JOptionPane.showMessageDialog(null,"ID Karyawan SUDAH TERINPUT");
        } else {
            String SQL = "INSERT INTO pelanggan (id_pelanggan, nama, no_telp, nama_perusahaan, alamat) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement simpan = getKoneksi().prepareStatement(SQL);
            simpan.setString(1, id_pelanggan);
            simpan.setString(2, nama);
            simpan.setString(3, no_telp);
            simpan.setString(4, nama_perusahaan);
            simpan.setString(5, alamat);
            simpan.executeUpdate();
            System.out.println("Data Berhasil Disimpan");
            simpan.close();
            getKoneksi().close();
        }
    } catch (Exception e) {
        System.out.println(e.toString());
    }
   } 
        
}
