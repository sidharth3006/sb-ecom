package com.ecommerce.project.repositories;

import com.ecommerce.project.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepositroy extends JpaRepository<Category,Long> {
    Category findByCategoryName(String categoryName);
}
