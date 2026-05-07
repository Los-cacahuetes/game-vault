package org.ies.tierno.logic;

import lombok.extern.log4j.Log4j;
import org.ies.tierno.jdbc.UserDAO;
import org.ies.tierno.model.User;

@Log4j
public class UserLogic {
    private UserDAO userDAO = new UserDAO();

    // Recargar sueldo
    public void addFunds(int id, double amount) {
        if (amount <= 0) {
            log.info("El importe debe ser mayor a 0");
        } else {
            User user = userDAO.findById(id);
            if (user != null) {
                double currentBalance = user.getBalance();
                double finalBalance = currentBalance + amount;
                userDAO.updateBalance(id, finalBalance);
                log.info("Recarga realizada con éxito");
                log.info("Su nuevo sado es de: " + finalBalance);
            } else {
                log.error("Usuario no encontrado");
            }
        }
    }
}
