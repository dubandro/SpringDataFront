package ru.geekbrains.dubandro.SpringDataFront.services;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.dubandro.SpringDataFront.dto.Cart;
import ru.geekbrains.dubandro.SpringDataFront.exceptions.ResourceNotFoundException;
import ru.geekbrains.dubandro.SpringDataFront.model.Product;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class CartService {
    private Cart tempCart;
    private final ProductService productService;
    private final OrderService orderService;

    @PostConstruct
    public void init() {
        tempCart = new Cart();
    }

    public Cart getCurrentCart() {
        return tempCart;
    }

    public void addProduct(Long productId, int quantity) {
        Product product = productService.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Can not add product with id " + productId + " to cart. Product not found"));
        tempCart.addProduct(product, quantity);
    }

    public void remove(Long productId) {
        tempCart.remove(productId);
    }

    public void clear() {
        tempCart.clearCart();
    }

    public void changeQuantity(Long id, int delta) {
        tempCart.changeQuantity(id, delta);
    }

    public void createOrder() {
        orderService.createOrder(tempCart);
        tempCart.clearCart();
    }
}
