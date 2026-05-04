package org.ies.tierno.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Bundle extends Product{
    public Bundle(int id, String name, String description, Double price, Double discount, LocalDate releaseDate, String type) {
        super(id, name, description, price, discount, releaseDate, type);
    }
}
