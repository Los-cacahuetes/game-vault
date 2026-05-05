package org.ies.tierno.jdbc;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/game_vault";
    private static final String USER = "user";
    private static final String PASSWORD = "contraseña";

    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            log.error("Error al obtener la conexión: {}", e.getMessage());
            throw e;
        }
    }

}
