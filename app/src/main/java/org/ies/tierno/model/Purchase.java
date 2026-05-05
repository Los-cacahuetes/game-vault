package org.ies.tierno.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Purchase {
    private int id;
    private int userId;
    private int gameId;
    private LocalDate date;
    private double totalPaid;
}
