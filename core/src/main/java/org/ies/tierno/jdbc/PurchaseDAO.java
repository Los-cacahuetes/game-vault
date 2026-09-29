package org.ies.tierno.jdbc;

import lombok.extern.log4j.Log4j;
import org.ies.tierno.model.Game;
import org.ies.tierno.model.Purchase;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Log4j
public class PurchaseDAO {
    public void create(int userId, int gameId, LocalDate date, double totalPaid) {
        String sql = "INSERT INTO purchase (user_id, game_id, date, amount_paid) VALUES (?, ?, ?, ?)";
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
                        rs.getInt("user_id"),
                        rs.getInt("game_id"),
                        rs.getObject("date", LocalDate.class),
                        rs.getDouble("amount_paid")
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
    // FIN CRUD ***********************************************************************************

    public boolean exists(int userId, int gameId) {
        String sql = "SELECT COUNT(*) FROM purchase WHERE userId = ? AND gameId = ?";
        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setInt(1, userId);
            ps.setInt(2, gameId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            log.error("Error al verificar existencai de compra: " + e.getMessage());
        }
        return false;
    }

    public List<Game> getUserGames(int userId) {
        List<Game> games = new ArrayList<>();
        String sql = "SELECT game_id FROM purchase WHERE user_id = ?";
        GameDAO gameDAO = new GameDAO();

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                int gameId = rs.getInt("game_id");

                Game game = gameDAO.findById(gameId);

                if (game != null) {
                    games.add(game);
                }
            }

        } catch (SQLException e) {
            log.error("No se ha podido encontrar los juegos");
        }
        return games;
    }
}
