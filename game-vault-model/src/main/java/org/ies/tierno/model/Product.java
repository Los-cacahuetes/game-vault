package org.ies.tierno.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
public abstract class Product {
    protected int id;
    protected String name;
    protected String description;
    protected Double price;
    protected Double discount;
    protected LocalDate releaseDate;
    protected String type;

}
