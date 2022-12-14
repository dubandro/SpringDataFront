package ru.geekbrains.dubandro.SpringDataFront.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.dubandro.SpringDataFront.model.Product;
import ru.geekbrains.dubandro.SpringDataFront.repositories.ProductRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> findAll(Double minPrice, Double maxPrice) {
        if (minPrice == null && maxPrice == null) return productRepository.findAll();
        if (minPrice == null) return productRepository.findAllByPriceIsLessThanEqual(maxPrice);
        if (maxPrice == null) return productRepository.findAllByPriceIsGreaterThanEqual(minPrice);
        return productRepository.findAllByPriceBetween(minPrice, maxPrice);
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Transactional
    public void changePrice(Long id, double newPrice) {
        Product product = productRepository.findById(id).orElseThrow();
        product.setPrice(newPrice);
    }
}