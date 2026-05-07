package org.ies.tierno.jdbc;

import lombok.extern.log4j.Log4j;
import org.ies.tierno.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j
public class UserDAO {
    // CRUD *****************************************************************************
    public void create(String username, String email, double balance) {
        String sql = "INSERT INTO user (username, email, balance) VALUES (?, ?, ?)";
        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, username);
            ps.setString(2, email);
            ps.setDouble(3, balance);
            ps.executeUpdate();
            log.info("Usuario creado con éxito");
        } catch (SQLException e) {
            log.error("Error al crear: " + e.getMessage());
        }
    }

    public List<User> read() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM user";

        try (
                Connection conn = DatabaseConnection.getConnection();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql);
        ) {
            while (rs.next()) {
                users.add(new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getDouble("balance")
                ));
            }

        } catch (SQLException e) {
            log.error("Error al leer: " + e.getMessage());
        }

        return users;
    }

    public void update(int id, String newUsername, String newEmail, double newBalance) {
        String sql = "UPDATE user SET username = ?, email = ?, balance = ? WHERE id = ?";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, newUsername);
            ps.setString(2, newEmail);
            ps.setDouble(3, newBalance);
            ps.setInt(4, id);
            ps.executeUpdate();
            log.info("Usuario actualizado correctamente");

        } catch (SQLException e) {
            log.error("Error al actualizar: " + e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM user WHERE id = ?";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0) log.info("Usuario eliminado");

        } catch (SQLException e) {
            log.error("Error al eliminar: " + e.getMessage());
        }
    }
    // FIN CRUD ********************************************************************************

    public User findById(int id) {
        String sql = "SELECT * FORM user WHERE id = ?";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getString("email"),
                            rs.getDouble("balance")
                    );
                }
            }
        } catch (SQLException e) {
            log.error("Error al buscar usuario: " + e.getMessage());
        }
        return null;
    }

    // Recargar saldo
    public void updateBalance(int id, double newBalance) {
        String sql = "UPDATE user SET balance = ? WHERE id = ?";
        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setDouble(1, newBalance);
            ps.setInt(2, id);
            ps.executeUpdate();
            log.info("Saldo actualizado con éxito");

        } catch (SQLException e) {
            log.error("Error al actualizar el saldo: " + e.getMessage());
        }
    }
}
