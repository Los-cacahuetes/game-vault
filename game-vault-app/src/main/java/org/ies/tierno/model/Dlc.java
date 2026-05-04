package org.ies.tierno.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Dlc extends Product{
    private int gameId;
    private double size;

    public Dlc(int id, String name, String description, Double price, Double discount, LocalDate releaseDate, String type, int gameId, double size) {
        super(id, name, description, price, discount, releaseDate, type);
        this.gameId = gameId;
        this.size = size;
    }
}
