package ru.geekbrains.dubandro.SpringDataFront.dto;


import ru.geekbrains.dubandro.SpringDataFront.model.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cart {
    private List<CartItem> itemList;
    private double totalPrice;

    public Cart() {
        this.itemList = new ArrayList<>();
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public List<CartItem> getItemList() {
        return Collections.unmodifiableList(itemList);
    }

    public void addProduct(Product product, int quantity) {
        for (CartItem item : itemList) {
            if (product.getId().equals(item.getProductId())) {
                item.changeQuantity(quantity);
                recalculate();
                return;
            }
        }
        itemList.add(new CartItem(product.getId(), product.getTitle(), quantity, product.getPrice(), product.getPrice() * quantity));
        recalculate();
    }



    public void remove(Long productId) {
        if (itemList.removeIf(item -> item.getProductId().equals(productId))) {
            recalculate();
        }
    }

    public void recalculate() {
        totalPrice = 0;
        for (CartItem item : itemList) {
            totalPrice += item.getItemTotalPrice();
        }
    }

    public void clearCart() {
        itemList.clear();
        totalPrice = 0;
    }

    public void changeQuantity(Long id, int delta) {
        for (CartItem item : itemList) {
            if (item.getProductId().equals(id)) {
                item.changeQuantity(delta);
                if (item.getQuantity() < 1) itemList.remove(item);
                recalculate();
                return;
            }
        }
    }
}
