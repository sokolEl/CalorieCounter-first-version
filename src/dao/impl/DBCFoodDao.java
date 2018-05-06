package dao.impl;

import dao.FoodDao;
import model.Food;

import java.sql.*;

public class DBCFoodDao implements FoodDao {
    private static String DB_URL = "jdbc:h2:tcp://localhost/~/Programm";

    public DBCFoodDao() {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Can't connect to DB");
        }

        try (Connection connection = DriverManager.getConnection(DB_URL, "Test", "");//почему нельзя один раз создать конекшен
             Statement st = connection.createStatement()) {
            st.execute("CREATE TABLE IF NOT EXISTS CALORIES(ID INT NOT NULL AUTO_INCREMENT, NAME_OF_PRODUCT VARCHAR(255), CALORIES_PER_SERVING INT, PRIMARY KEY (id));");//добавить id
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }


    @Override
    public void addFoodDBC(Food newProduct) {
        try (Connection connection = DriverManager.getConnection(DB_URL, "Test", "");
             PreparedStatement st = connection.prepareStatement("INSERT INTO CALORIES VALUES(default,?,?);")) {

            st.setString(1, newProduct.getFoodName());
            st.setInt(2, newProduct.getCal());
            st.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public int getCalories(String name) {

        try (Connection connection = DriverManager.getConnection(DB_URL, "Test", "");
             PreparedStatement st = connection.prepareStatement("SELECT CALORIES_PER_SERVING  FROM CALORIES WHERE NAME_OF_PRODUCT=?;")) {
            st.setString(1, name);
            ResultSet rs = st.executeQuery();
            rs.next();
            return rs.getInt("CALORIES_PER_SERVING");
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }


    public void deleteFoodDBC(String name) {
        try (Connection connection = DriverManager.getConnection(DB_URL, "Test", "");
             PreparedStatement st = connection.prepareStatement("DELETE CALORIES WHERE NAME_OF_PRODUCT=?;")) {
            st.setString(1, name);
            st.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public boolean isProductDBC(String name) {

        try (Connection connection = DriverManager.getConnection(DB_URL, "Test", "");
             PreparedStatement st = connection.prepareStatement("SELECT CALORIES_PER_SERVING  FROM CALORIES WHERE NAME_OF_PRODUCT=?;")) {
            st.setString(1, name);
            ResultSet rs = st.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    }




