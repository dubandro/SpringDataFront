package ru.geekbrains.dubandro.SpringDataFront.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.dubandro.SpringDataFront.dto.Cart;
import ru.geekbrains.dubandro.SpringDataFront.model.Order;
import ru.geekbrains.dubandro.SpringDataFront.services.CartService;
import ru.geekbrains.dubandro.SpringDataFront.services.OrderService;

import java.util.List;

@RestController
@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;
    private final OrderService orderService;

    @GetMapping()
    public Cart getCurrentCart() {
        return cartService.getCurrentCart();
    }

    @GetMapping("/add/{id}")
    public void addToCart(@PathVariable Long id) {
        cartService.addProduct(id, 1);
    }

    @GetMapping("/clear")
    public void clearCart() {
        cartService.clear();
    }

    @GetMapping("/delete/{id}")
    public void removeFromCart(@PathVariable Long id) {
        cartService.remove(id);
    }

    @GetMapping("/change")
    public void changeQuantity(@RequestParam Long id, @RequestParam int delta) {
        cartService.changeQuantity(id, delta);
    }

    //---Orders
    @GetMapping("/order")
    public void createOrder() {
        cartService.createOrder();
    }

    @GetMapping("/order/all")
    public List<Order> ordersList() {
        return orderService.findAll();
    }
}
