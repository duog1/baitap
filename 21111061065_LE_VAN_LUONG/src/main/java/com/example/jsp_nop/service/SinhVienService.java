package com.example.jsp_nop.service;

import com.example.jsp_nop.connect.ConnectionDB;
import com.example.jsp_nop.model.SinhVien;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SinhVienService {

    private static final String INSERT_SINH_VIEN = "INSERT INTO QLSinhvien(Tensv, Lop, Quequan) VALUES (?, ?, ?);";
    private static final String SELECT_SINH_VIEN = "SELECT * FROM QLSinhvien;";
    private static final String UPDATE_SINH_VIEN = "UPDATE QLSinhvien SET Tensv=?, Lop=?, Quequan=? WHERE Masv=?;";
    private static final String DELETE_SINH_VIEN = "DELETE FROM QLSinhvien WHERE Masv=?;";
    private static final String SEARCH_SINH_VIEN_BY_NAME = "SELECT * FROM QLSinhvien WHERE Tensv LIKE ?;";


    public Boolean themSinhVien(SinhVien sinhVien) {
        Connection connection = null;
        try {
            connection = ConnectionDB.openConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SINH_VIEN);
            preparedStatement.setString(1, sinhVien.getTenSinhVien());
            preparedStatement.setString(2, sinhVien.getLopSinhVien());
            preparedStatement.setString(3, sinhVien.getQueQuanSinhVien());
            preparedStatement.executeUpdate();
            connection.commit();
            ConnectionDB.closeConnection(connection, preparedStatement, null);
            return true;
        } catch (SQLException e) {
            try {
                connection.rollback();
                System.out.println("rollback SUCCESS");
            } catch (SQLException ex) {
                System.out.println("rollback ERROR");
            }
            ConnectionDB.closeConnection(connection, null, null);
            return false;
        }
    }

    public List<SinhVien> layDanhSachSinhVien() {
        List<SinhVien> sinhVienList = new ArrayList<>();
        try {
            Connection connection = ConnectionDB.openConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SINH_VIEN);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                SinhVien sinhVien = new SinhVien();
                sinhVien.setId(resultSet.getLong("Masv"));
                sinhVien.setTenSinhVien(resultSet.getString("Tensv"));
                sinhVien.setLopSinhVien(resultSet.getString("Lop"));
                sinhVien.setQueQuanSinhVien(resultSet.getString("Quequan"));
                sinhVienList.add(sinhVien);
            }
            ConnectionDB.closeConnection(connection, preparedStatement, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sinhVienList;
    }

    public SinhVien laySinhVienTheoId(Long id) {
        SinhVien sinhVien = null;
        Connection connection = null;

        try {
            connection = ConnectionDB.openConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM QLSinhvien WHERE Masv = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                sinhVien = new SinhVien();
                sinhVien.setId(resultSet.getLong("Masv"));
                sinhVien.setTenSinhVien(resultSet.getString("Tensv"));
                sinhVien.setLopSinhVien(resultSet.getString("Lop"));
                sinhVien.setQueQuanSinhVien(resultSet.getString("Quequan"));
            }

            ConnectionDB.closeConnection(connection, preparedStatement, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sinhVien;
    }


    public Boolean xoaSinhVien(Long id) {
        Connection connection = null;
        try {
            connection = ConnectionDB.openConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SINH_VIEN);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            connection.commit();
            ConnectionDB.closeConnection(connection, preparedStatement, null);
            return true;
        } catch (SQLException e) {
            try {
                connection.rollback();
                System.out.println("rollback SUCCESS");
            } catch (SQLException ex) {
                System.out.println("rollback ERROR");
            }
            ConnectionDB.closeConnection(connection, null, null);
            return false;
        }
    }



    public List<SinhVien> timKiemSinhVien(String keyword) {
        List<SinhVien> sinhVienList = new ArrayList<>();
        Connection connection = null;

        try {
            connection = ConnectionDB.openConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_SINH_VIEN_BY_NAME);
            preparedStatement.setString(1, "%" + keyword + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                SinhVien sinhVien = new SinhVien();
                sinhVien.setId(resultSet.getLong("Masv"));
                sinhVien.setTenSinhVien(resultSet.getString("Tensv"));
                sinhVien.setLopSinhVien(resultSet.getString("Lop"));
                sinhVien.setQueQuanSinhVien(resultSet.getString("Quequan"));
                sinhVienList.add(sinhVien);
            }

            ConnectionDB.closeConnection(connection, preparedStatement, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sinhVienList;
    }
    public Boolean capNhatSinhVien(SinhVien sinhVien) {
        Connection connection = null;
        try {
            connection = ConnectionDB.openConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SINH_VIEN);
            preparedStatement.setString(1, sinhVien.getTenSinhVien());
            preparedStatement.setString(2, sinhVien.getLopSinhVien());
            preparedStatement.setString(3, sinhVien.getQueQuanSinhVien());
            preparedStatement.setLong(4, sinhVien.getId());
            preparedStatement.executeUpdate();
            connection.commit();
            ConnectionDB.closeConnection(connection, preparedStatement, null);
            return true;
        } catch (SQLException e) {
            try {
                connection.rollback();
                System.out.println("Rollback success");
            } catch (SQLException ex) {
                System.out.println("Rollback error");
            }
            ConnectionDB.closeConnection(connection, null, null);
            return false;
        }
    }



}
