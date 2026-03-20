package com.ecommerce.project.controller;

import com.ecommerce.project.config.AppConstants;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.payload.CategoryDTO;
import com.ecommerce.project.payload.CategoryResponse;
import com.ecommerce.project.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Tag(name = "Categories", description = "Endpoints for managing categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Operation(summary = "Get all categories", description = "Retrieve paginated list of categories (public access)")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved categories")
    @GetMapping("/public/categories")
    public ResponseEntity<CategoryResponse> getAllCategories(
            @Parameter(description = "Page number (default 0)")
            @RequestParam(name="pageNumber",defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @Parameter(description = "Page size (default 10)")
            @RequestParam(name="pageSize",defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @Parameter(description = "Sort field (default categoryId)")
            @RequestParam(name="sortBy",defaultValue = AppConstants.SORT_CATEGORIES_BY, required = false) String sortBy,
            @Parameter(description = "Sort direction (asc/desc)")
            @RequestParam(name="sortOrder",defaultValue = AppConstants.SORT_DIR,required = false) String sortOrder
                                                             ){
        CategoryResponse categoryResponse = categoryService.getAllCategories(pageNumber,pageSize,sortBy,sortOrder);
        return new ResponseEntity<>(categoryResponse,HttpStatus.OK);
    }
    @Operation(summary = "Create category", description = "Add a new category (public access)")
    @ApiResponse(responseCode = "200", description = "Category created successfully")
    @PostMapping("/public/categories")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO){
        CategoryDTO savedCategoryDTO = categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(savedCategoryDTO,HttpStatus.OK);
    }

    @Operation(summary = "Delete category", description = "Delete category by ID (admin only)")
    @ApiResponse(responseCode = "200", description = "Category deleted successfully")
    @DeleteMapping("/admin/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable Long categoryId){
            CategoryDTO deletedCategoryDTO = categoryService.deleteCategory(categoryId);
            return new ResponseEntity<>(deletedCategoryDTO, HttpStatus.OK);
    }

    @Operation(summary = "Update category", description = "Update category by ID (public access)")
    @ApiResponse(responseCode = "200", description = "Category updated successfully")
    @PutMapping("/public/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryDTO categoryDTO, @PathVariable Long categoryId){
            CategoryDTO savedCategoryDTO = categoryService.updateCategory(categoryDTO,categoryId);
            return new ResponseEntity<>(savedCategoryDTO,HttpStatus.OK);
    }
}
