package org.ies.tierno.logic;

import lombok.extern.log4j.Log4j;
import org.ies.tierno.jdbc.GameDAO;
import org.ies.tierno.model.Game;

import java.util.List;

@Log4j
public class GameLogic {
    private GameDAO gameDAO = new GameDAO();

    public void applyDiscount(int id, int discount_pct) {
        Game game = gameDAO.findById(id);

        if (game == null) {
            log.error("El juego no existe");
            return;
        }

        if (discount_pct > 100) {
            log.error("No se puede aplicar un descuento mayor a 100%");
            return;
        }

        gameDAO.updateDiscount(id, discount_pct);
    }

    // TODO: Agregar filtro por género
    public void showShop() {
        List<Game> games = gameDAO.read();

        if (games.isEmpty()) {
            log.info("No hay ningun juego en la tienda");
            return;
        }

        for (Game game : games) {
            double discountAmount = game.getPrice() * (game.getDiscountPct() / 100d);
            double finalPrice = game.getPrice() - discountAmount;
            log.info("ID. " + game.getId() + " | " + game.getName() + " | " + game.getGenre() + " | " + finalPrice + "€");
        }
    }
}
