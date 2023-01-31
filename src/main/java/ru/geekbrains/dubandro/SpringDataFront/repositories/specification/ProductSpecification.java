package ru.geekbrains.dubandro.SpringDataFront.repositories.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.dubandro.SpringDataFront.model.Product;

public class ProductSpecification {

    public static Specification<Product> lessOrEqual(Double maxPrice) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice));
    }

    public static Specification<Product> greaterOrEqual(Double minPrice) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice));
    }

    public static Specification<Product> nameLike(String partTitle){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), String.format("%%%s%%", partTitle)));
    }
}
