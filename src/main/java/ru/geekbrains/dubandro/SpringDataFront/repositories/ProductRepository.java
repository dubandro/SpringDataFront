package ru.geekbrains.dubandro.SpringDataFront.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.geekbrains.dubandro.SpringDataFront.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByPriceBetween(double minPrice, double maxPrice);
    List<Product> findAllByPriceIsLessThanEqual(double maxPrice);
    List<Product> findAllByPriceIsGreaterThanEqual(double minPrice);

    @Query("select p from Product p where p.category = :category") // будет работать и без запроса
    List<Product> findAllByCategory(String category);
}
