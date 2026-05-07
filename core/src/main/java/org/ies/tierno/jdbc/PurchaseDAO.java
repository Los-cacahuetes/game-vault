package org.ies.tierno.jdbc;

import lombok.extern.log4j.Log4j;
import org.ies.tierno.model.Purchase;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Log4j
public class PurchaseDAO {
    public void create(int userId, int gameId, LocalDate date, double totalPaid) {
        String sql = "INSERT INTO purchase (userId, gameId, date, totalPaid) VALUES (?, ?, ?, ?)";
        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setInt(1, userId);
            ps.setInt(2, gameId);
            ps.setObject(3, date);
            ps.setDouble(4, totalPaid);
            ps.executeUpdate();
            log.info("Compra creada con éxito");
        } catch (SQLException e) {
            log.error("Error al crear: " + e.getMessage());
        }
    }

    public List<Purchase> read() {
        List<Purchase> purchases = new ArrayList<>();
        String sql = "SELECT * FROM purchase";

        try (
                Connection conn = DatabaseConnection.getConnection();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql);
        ) {
            while (rs.next()) {
                purchases.add(new Purchase(
                        rs.getInt("id"),
                        rs.getInt("userId"),
                        rs.getInt("gameId"),
                        rs.getObject("date", LocalDate.class),
                        rs.getDouble("totalPaid")
                ));
            }
        } catch (SQLException e) {
            log.error("Error al leer: " + e.getMessage());
        }

        return purchases;
    }

    public void update(int id, int userId, int gameId, LocalDate date, double totalPaid) {
        String sql = "UPDATE purchase SET user_id = ?, game_id = ?, date = ?, amount_paid = ? WHERE id = ?";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setInt(1, userId);
            ps.setInt(2, gameId);
            ps.setObject(3, date);
            ps.setDouble(4, totalPaid);
            ps.setInt(5, id);
            ps.executeUpdate();
            log.info("Compra actualizada correctamente");
        } catch (SQLException e) {
            log.error("Error al actualizar: " + e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM purchase WHERE id = ?";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0) log.info("Compra eliminada con éxito");
        } catch (SQLException e) {
            log.error("Error al eliminar: " + e.getMessage());
        }
    }
}
