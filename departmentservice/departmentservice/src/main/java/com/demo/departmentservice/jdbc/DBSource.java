package com.demo.departmentservice.jdbc;

import com.demo.departmentservice.config.EnvParser;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class DBSource {

    @Autowired
    private EnvParser parser;

    private String dbUrl;
    private String dbUser;
    private String dbPass;

    @PostConstruct
    private void init() {
        dbUrl = parser.getDbUrl();      // Example: "jdbc:mysql://localhost:3306/dbname"
        dbUser = parser.getDbUser();
        dbPass = parser.getDbPassword();
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, dbUser, dbPass);
    }


    public void insertDepartment(Long id, String name, String description, Long managerId) {
        String sql = "INSERT INTO department (id,name, description, manager_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection();  // Use your DBSource to get connection
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.setString(2, name);
            stmt.setString(3, description);
            stmt.setLong(4, managerId);

            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("A new department was inserted successfully!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDepartment(Long id) {
        String sql = "DELETE FROM department WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Department with ID " + id + " was deleted successfully!");
            } else {
                System.out.println("No department found with ID: " + id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
