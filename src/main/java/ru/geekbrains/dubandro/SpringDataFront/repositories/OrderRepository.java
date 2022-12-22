package ru.geekbrains.dubandro.SpringDataFront.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.dubandro.SpringDataFront.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
