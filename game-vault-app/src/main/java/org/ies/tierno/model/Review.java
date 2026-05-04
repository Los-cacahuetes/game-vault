package org.ies.tierno.model;

import java.time.LocalDate;

public class Review {
    private int id;
    private int userId;
    private int productId;
    private int rating;
    private String comment;
    private LocalDate date;
    private boolean isRecommended;
}
