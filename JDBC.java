package org.example;

import java.sql.*;

public class JDBC {
    private final static String USERNAME = "postgres"; // учетная запись пользователя
    private final static String PASSWORD = "123"; // пароль
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static Connection connection;
    private static PreparedStatement preparedStatement = null;
    public  static int putSum = 20000;
    public static int ID = 1;
    public static int takeSum = 10;

    public static int res;

    static {
        try{
            Class.forName("org.postgresql.Driver");
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static int getBalance(int ID) {
        try {
            preparedStatement = connection.prepareStatement("select balance from user_data where ID = ?");
            preparedStatement.setInt(1, ID);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                res = resultSet.getInt(1);
            }
            if (res > 0){
                System.out.println("Баланс: " + res);
            }
            else {
                System.out.println("Недостаточно средств");
            return -1;
            }


        } catch (SQLException e) {
            System.out.println("Что-то не,то с getBalance");
            return -1;
        }
        return 0;
    }
    public static int takeMoney(int ID, int takeSum){
        String sql = "update user_data set balance = balance-? where ID = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,takeSum);
            preparedStatement.setInt(2,ID);
            int resultSet = preparedStatement.executeUpdate();
                System.out.println("take:" + resultSet);
                return 1;
        } catch (SQLException e) {
            System.out.println("Проблемы с takeMoney");
            return -1;
        }
    }
    public static  int putMoney(int ID, int putSum){
        String sql = "update user_data set balance = balance+? where ID = ?";
        try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, putSum);
                preparedStatement.setInt(2, ID);
            int resultSet = preparedStatement.executeUpdate();
            System.out.println("put " + resultSet);
            return 1;
        } catch (SQLException e) {
            System.out.println("Проблемы с putMoney");
            return 0;
        }
    }
}


