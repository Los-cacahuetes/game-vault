package org.ies.tierno.jdbc;

import lombok.extern.log4j.Log4j;
import org.ies.tierno.model.Game;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j
public class GameDAO {
    // CRUD ***************************************************************************************
    public void create(String name, String genre, double price, int discountPct) {
        String sql = "INSTERT INTO game (name, genre, price, discount_percentage) VALUES (?, ?, ?, ?)";
        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, name);
            ps.setString(2, genre);
            ps.setDouble(3, price);
            ps.setInt(4, discountPct);
            ps.executeUpdate();
            log.info("Juego creado con éxito");
        } catch (SQLException e) {
            log.error("Error al crear: " + e.getMessage());
        }
    }

    public List<Game> read() {
        List<Game> games = new ArrayList<>();
        String sql = "SELECT * FROM game";

        try (
                Connection conn = DatabaseConnection.getConnection();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql);
        ) {
            while (rs.next()) {
                games.add(new Game(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("genre"),
                        rs.getDouble("price"),
                        rs.getInt("discount_percentage")
                ));
            }

        } catch (SQLException e) {
            log.error("Error al leer: " + e.getMessage());
        }
        return games;
    }

    public void update(int id, String name, String genre, double price, int discountPct) {
        String sql = "UPDATE game SET name = ?, genre = ?, price = ?, discount_percentage WHERE ID = ?";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, name);
            ps.setString(2, genre);
            ps.setDouble(3, price);
            ps.setInt(4, id);
            ps.executeUpdate();
            log.info("Juego modificado con éxito");
        } catch (SQLException e) {
            log.error("Error al actualizar: " + e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM game WHERE id = ?";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0) log.info("Juego eliminado");
        } catch (SQLException e) {
            log.error("Error al eliminar " + e.getMessage());
        }
    }
    // FIN CRUD ***********************************************************************************

    public Game findById(int id) {
        String sql = "SELECT * FROM game WHERE id = ?";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Game(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("genre"),
                            rs.getDouble("price"),
                            rs.getInt("discount_percentage")
                    );
                }
            }
        } catch (SQLException e) {
            log.error("Error al buscar juego: " + e.getMessage());
        }
        return null;
    }

    public void updateDiscount(int gameId, int discountPct) {
        String sql = "UPDATE game SET discount_percentage = ? WHERE id = ?";
        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setInt(1, discountPct);
            ps.setInt(2, gameId);
            ps.executeUpdate();
            log.info("Descuento del " + discountPct + "% aplicado al juego");

        } catch (SQLException e) {
            log.error("Error al actualizar el descuento: " + e.getMessage());
        }
    }
}
