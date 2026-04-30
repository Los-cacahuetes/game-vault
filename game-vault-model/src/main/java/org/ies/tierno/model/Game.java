package org.ies.tierno.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Game extends Product{
    private String genre;
    private String developer;
    private String editor;
    private String platform;
    private String pegi;
    private String minRequirement;

    public Game(int id, String name, String description, Double price, Double discount, LocalDate releaseDate, String type, String genre, String developer, String editor, String platform, String pegi, String minRequirement) {
        super(id, name, description, price, discount, releaseDate, type);
        this.genre = genre;
        this.developer = developer;
        this.editor = editor;
        this.platform = platform;
        this.pegi = pegi;
        this.minRequirement = minRequirement;
    }
}
