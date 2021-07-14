/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bai1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * 
 * @author TUAN ANH
 */
public class SanPhamDAO {
    private Connection conn;
    private static String DB_URL = "jdbc:mysql://localhost:3306/bai1";
    private static String USER_NAME = "root";
    private static String PASSWORD = "123456789";
    
    public static Connection getConnection(String dbURL, String userName, 
            String password) {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, userName, password);
            System.out.println("connect successfully!");
        } catch (Exception ex) {
            System.out.println("connect failure!");
            ex.printStackTrace();
        }
        return conn;
    }

    public SanPhamDAO() {
        try {
            conn = getConnection(DB_URL, USER_NAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public ArrayList<SanPham> dsSanPham() {
        ArrayList<SanPham> list = new ArrayList<>();
        String sql = "select * from LoaiSanPham inner join SanPham "
                + "on LoaiSanPham.MaLoaiSP = SanPham.MaLoaiSP";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPham s = new SanPham();
                s.setMaSP(rs.getString("MaSP"));
                s.setTenSP(rs.getString("TenSP"));
                s.setNhaSanXuat(rs.getString("NhaSanXuat"));
                s.setMaLoaiSP(rs.getString("MaLoaiSP"));
                s.setTenLoaiSP(rs.getString("TenLoaiSP"));
                
                list.add(s);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
