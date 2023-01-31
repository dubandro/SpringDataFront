package ru.geekbrains.dubandro.SpringDataFront.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.dubandro.SpringDataFront.model.Product;
import ru.geekbrains.dubandro.SpringDataFront.repositories.ProductRepository;
import ru.geekbrains.dubandro.SpringDataFront.repositories.specification.ProductSpecification;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Page<Product> findProducts(Integer pageIndex, Double minPrice, Double maxPrice, String partTitle) {
        Specification<Product> spec = Specification.where(null);
        if (minPrice != null) {
            spec = spec.and(ProductSpecification.greaterOrEqual(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(ProductSpecification.lessOrEqual(maxPrice));
        }
        if (partTitle != null) {
            spec = spec.and(ProductSpecification.nameLike(partTitle));
        }
        return productRepository.findAll(spec, PageRequest.of(pageIndex - 1, 6));
//        return productRepository.findAll(PageRequest.of(pageIndex - 1, 10));

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

    // old method
    public List<Product> findAll(Double minPrice, Double maxPrice) {
        if (minPrice == null && maxPrice == null) return productRepository.findAll();
        if (minPrice == null) return productRepository.findAllByPriceIsLessThanEqual(maxPrice);
        if (maxPrice == null) return productRepository.findAllByPriceIsGreaterThanEqual(minPrice);
        return productRepository.findAllByPriceBetween(minPrice, maxPrice);
    }
}