package com.example.jsp_nop.connect;

import java.sql.*;

public class ConnectionDB {
    private static final String URL_DB = "jdbc:mysql://localhost:3306/hunre";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "";

    public static Connection openConnection(){
            Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL_DB,USER_NAME,PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
    public static void closeConnection(Connection connection,
                                       PreparedStatement preparedStatement,
                                       ResultSet resultSet){
        try{
            if (connection != null){
                connection.close();
            }
            if (preparedStatement != null){
                preparedStatement.close();
            }
            if (resultSet != null)
            {
                resultSet.close();}
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
