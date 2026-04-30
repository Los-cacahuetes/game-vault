package org.ies.tierno.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Library {
    private int userId;
    private int productId;
    private LocalDate purchaseDate;
    private int hoursPlayed;
    private LocalDate lastSession;
}
