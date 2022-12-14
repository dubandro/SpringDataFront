package ru.geekbrains.dubandro.SpringDataFront.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.dubandro.SpringDataFront.model.Product;

@Data
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String title;
    private double price;
    private String categoryTitle;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.categoryTitle = product.getCategory().getTitle();
    }
}
