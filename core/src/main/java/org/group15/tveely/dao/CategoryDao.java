package org.group15.tveely.dao;

import org.group15.tveely.CategoryEntity;

import java.util.Optional;


public interface CategoryDao {
    Optional<CategoryEntity> findByCategory(String category);

}


