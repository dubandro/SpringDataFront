package ru.geekbrains.dubandro.SpringDataFront.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    private Long productId;
    private String productTitle;
    private int quantity;
    private double productPrice;
    private double itemTotalPrice;

    public void changeQuantity(int delta) {
        quantity += delta;
        itemTotalPrice = quantity * productPrice;
    }
}
