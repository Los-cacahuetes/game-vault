package org.ies.tierno.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Cosmetic extends Product{
    private int gameId;
    private String rarity;

    public Cosmetic(int id, String name, String description, Double price, Double discount, LocalDate releaseDate, String type, int gameId, String rarity) {
        super(id, name, description, price, discount, releaseDate, type);
        this.gameId = gameId;
        this.rarity = rarity;
    }
}
