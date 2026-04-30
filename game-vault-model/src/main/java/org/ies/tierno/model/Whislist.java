package org.ies.tierno.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Whislist {
    private int userId;
    private int productId;
    private LocalDate addedDate;
}
