package org.group15.tveely.mappers;

import lombok.AllArgsConstructor;
import org.group15.tveely.CategoryEntity;
import org.group15.tveely.dao.CategoryDao;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class CategoryToCategoryEntity {
    private final CategoryDao categoryDao;

    public Optional<CategoryEntity> map(String categoryName) {
        return categoryDao.findByCategory(categoryName);
    }


}



