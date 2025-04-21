package org.group15.tveely.dao;

import lombok.AllArgsConstructor;
import org.group15.tveely.CategoryEntity;
import org.group15.tveely.repository.CategoryRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
@AllArgsConstructor
public class CategoryDaoImpl implements CategoryDao{

    private final CategoryRepository categoryRepository;

    @Override
    public Optional<CategoryEntity> findByCategory(String category){
        return categoryRepository.findByCategory(category);
    }



}
