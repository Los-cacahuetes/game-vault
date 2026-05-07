package org.ies.tierno.logic;

import lombok.extern.log4j.Log4j;
import org.ies.tierno.jdbc.GameDAO;
import org.ies.tierno.jdbc.PurchaseDAO;
import org.ies.tierno.jdbc.UserDAO;
import org.ies.tierno.model.Game;
import org.ies.tierno.model.Purchase;
import org.ies.tierno.model.User;

import java.time.LocalDate;
import java.util.List;

@Log4j
public class PurchaseLogic {
    private UserDAO userDAO = new UserDAO();
    private GameDAO gameDAO = new GameDAO();
    private PurchaseDAO purchaseDAO = new PurchaseDAO();

    public void purhcaseGame(int userId, int gameId) {
        User user = userDAO.findById(userId);
        if (user == null) {
            log.error("El usuario no existe");
            return;
        }

        Game game = gameDAO.findById(gameId);
        if (game == null) {
            log.error("El juego no existe");
            return;
        }

        if (purchaseDAO.exists(userId, gameId)) {
            log.error("El usuario ya posee el juego");
            return;
        }

        double balance = user.getBalance();
        double discountPct = game.getDiscountPct();
        double price = game.getPrice() - discountPct / 100;

        if (balance < price) {
            log.error("Saldo insuficiente");
            return;
        }

        double newBalance = balance - price ;
        userDAO.updateBalance(userId, newBalance);

        purchaseDAO.create(userId, gameId, LocalDate.now(), price);

        log.info("Compra realizada con éxito");
    }

    public void showLibrary(int userId) {
        User user = userDAO.findById(userId);

        if (user == null) {
            log.info("Usuario no encontrado");
            return;
        }

        List<Game> library = purchaseDAO.getUserGames(userId);

        if (library.isEmpty()) {
            log.info("El usuario no posee ningún juego en su biblioteca");
            return;
        }

        for (Game game: library) {
            log.info(game);
        }
    }
}
