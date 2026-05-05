package org.ies.tierno.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Game{
    private int id;
    private String name;
    private String genre;
    private double price;
}
