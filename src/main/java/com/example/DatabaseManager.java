package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DatabaseManager {
    private static final String URL = "jdbc:sqlite:zoo.db";

    public static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS animals ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "name TEXT NOT NULL, "
                + "age INTEGER, "
                + "species TEXT NOT NULL);";

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table 'animals' created successfully!");
        } catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
        }
    }

     public static void insertAnimal(String name, int age, String species) {
        String sql = "INSERT INTO animals(name, age, species) VALUES(?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setString(3, species);
            pstmt.executeUpdate();
            System.out.println(name + " added to database.");
        } catch (SQLException e) {
            System.out.println("Error inserting data: " + e.getMessage());
        }
    }

     public static void getAllAnimals() {
        String sql = "SELECT * FROM animals";

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                                   ", Name: " + rs.getString("name") +
                                   ", Age: " + rs.getInt("age") +
                                   ", Species: " + rs.getString("species"));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving data: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        createTable();  // Ensures the table exists first

        // Add animals to database
        insertAnimal("Simba", 5, "Lion");
        insertAnimal("Dumbo", 10, "Elephant");
        insertAnimal("Polly", 2, "Parrot");

        System.out.println("----- All Animals in Zoo -----");
        getAllAnimals();
    }
}