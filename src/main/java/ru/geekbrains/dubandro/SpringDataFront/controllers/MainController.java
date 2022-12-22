package ru.geekbrains.dubandro.SpringDataFront.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.dubandro.SpringDataFront.dto.ProductDto;
import ru.geekbrains.dubandro.SpringDataFront.exceptions.ArgsEntityException;
import ru.geekbrains.dubandro.SpringDataFront.exceptions.ResourceNotFoundException;
import ru.geekbrains.dubandro.SpringDataFront.model.Category;
import ru.geekbrains.dubandro.SpringDataFront.model.Product;
import ru.geekbrains.dubandro.SpringDataFront.services.CategoryService;
import ru.geekbrains.dubandro.SpringDataFront.services.ProductService;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping("/products")
    @ResponseBody
    public Page<ProductDto> productToCriteria(
            @RequestParam(name = "min", required = false) Double minPrice,
            @RequestParam(name = "max", required = false) Double maxPrice,
            @RequestParam(name = "title", required = false) String partTitle,
            @RequestParam(name = "p", defaultValue = "1") int pageIndex) {
        if (pageIndex < 1) {
            pageIndex = 1;
        }
        return productService
                .findProducts(pageIndex, minPrice, maxPrice, partTitle)
                .map(ProductDto::new);
    }

    @GetMapping("/market-map")
    public String mainPage() {
        return "map";
    }

    @GetMapping("/create-product")
    public String createForm(Model model) {
//        model.addAttribute("nextID", productService.nextID());
        return "createProduct";
    }

    @PostMapping("/create-product")
    public String addToCatalog(Model model,
                               @RequestParam String title,
                               @RequestParam double price,
                               @RequestParam Long category_id)
    {
        Product product = new Product();
        product.setTitle(title);
        product.setPrice(price);
        Category category = categoryService.findById(category_id).orElseThrow(() ->
                new ArgsEntityException("Category with id:" + category_id + " does not exist"));
        product.setCategory(category);
        productService.save(product);
        model.addAttribute("product", new ProductDto(product));
        return "productPage";
//        return "redirect:/product/{id}";
    }

    @GetMapping("/products/delete")
    @ResponseBody
    public void deleteProduct(@RequestParam Long id) {
        productService.deleteById(id);
    }

    @GetMapping("/change_price")
    @ResponseBody
    public void changePrice(@RequestParam Long id, @RequestParam double delta) {
        double newPrice = productService.findById(id).get().getPrice() + delta;
        productService.changePrice(id, newPrice);
    }

    @GetMapping("/product/{id}")
    public String product(Model model, @PathVariable Long id) {
        ProductDto productDto = new ProductDto(productService.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Product with id:" + id + " does not exist")));
        model.addAttribute("product", productDto);
        return "productPage";
    }

    /**
     * Old methods for json use
     */

    //old method
    @GetMapping("/json/products")
    @ResponseBody
    public List<ProductDto> findAll(
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice
    ) {
        return productService
                .findAll(minPrice, maxPrice)
                .stream()
                .map(ProductDto::new)
                .collect(Collectors.toList());
    }

    //old method
    @GetMapping("/json/{id}")
    @ResponseBody
    public ProductDto findById(@PathVariable Long id) {
        return new ProductDto(productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product with id:" + id + " does not exist")));
    }

    //old method
    @PostMapping("/json/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto save(@RequestBody ProductDto productDto) {
        Product product = new Product();
        product.setPrice(productDto.getPrice());
        product.setTitle(productDto.getTitle());
        Category category = categoryService.findByTitle(productDto.getCategoryTitle()).get();
        product.setCategory(category);
        productService.save(product);
        return new ProductDto(product);
    }

    //old method
    @GetMapping("/json/delete/{id}")
    @ResponseBody
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    //old method
    @PostMapping("/json/{id}/newprice/{newPrice}")
    @ResponseBody
    public ProductDto changePriceJson(@PathVariable Long id, @PathVariable double newPrice) {
        productService.changePrice(id, newPrice);
        return new ProductDto(productService.findById(id).get());
    }
}
