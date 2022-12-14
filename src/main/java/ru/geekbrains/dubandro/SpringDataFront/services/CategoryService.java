package ru.geekbrains.dubandro.SpringDataFront.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.dubandro.SpringDataFront.model.Category;
import ru.geekbrains.dubandro.SpringDataFront.repositories.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Optional<Category> findByTitle(String title) {
        return categoryRepository.findByTitle(title);
    }

    public Optional<Category> findById(Long category_id) {
        return categoryRepository.findById(category_id);
    }
}
