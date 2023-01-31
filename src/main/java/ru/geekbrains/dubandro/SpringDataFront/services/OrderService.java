package ru.geekbrains.dubandro.SpringDataFront.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.dubandro.SpringDataFront.dto.Cart;
import ru.geekbrains.dubandro.SpringDataFront.model.Order;
import ru.geekbrains.dubandro.SpringDataFront.repositories.OrderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public void createOrder(Cart tempCart) {
        Order order = new Order();
        order.setTotalPrice(tempCart.getTotalPrice());
        orderRepository.save(order);
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }
}
