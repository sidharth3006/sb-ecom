package com.ecommerce.project.service;

import com.ecommerce.project.exceptions.APIException;
import com.ecommerce.project.exceptions.ResourceNotFoundException;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.repositories.CategoryRepositroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepositroy categoryRepositroy;

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = categoryRepositroy.findAll();
        if (categories.isEmpty())
            throw new APIException("No category created till now");

        return categoryRepositroy.findAll();
    }

    @Override
    public void createCategory(Category category) {
        Category savedCategory = categoryRepositroy.findByCategoryName(category.getCategoryName());
        if (savedCategory != null)
            throw new APIException("Category with the name "+category.getCategoryName() + " already exists!!!");
        categoryRepositroy.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId){
        Category category = categoryRepositroy.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category","categoryId",categoryId));
        categoryRepositroy.delete(category);
        return "Category with categoryId: "+categoryId + "deleted successfully";
    }

    @Override
    public Category updateCategory(Category category,Long categoryId) {
         Category savedcategory = categoryRepositroy.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category","categoryId",categoryId));
         category.setCategoryId(categoryId);
         savedcategory = categoryRepositroy.save(category);
         return savedcategory;
    }

}
