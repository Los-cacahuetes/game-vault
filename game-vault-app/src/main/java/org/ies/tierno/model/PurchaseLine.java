package org.ies.tierno.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PurchaseLine {
    private int id;
    private int purchaseId;
    private int productId;
    private double pricePaid;
}
