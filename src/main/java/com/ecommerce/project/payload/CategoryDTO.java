package com.ecommerce.project.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    @Schema(description = "Category ID for a particular category", example = "101")
    private Long categoryId;

    @NotBlank
    @Size(min = 5, message = "Category must container atleast 5 character")
    @Schema(description = "Category name for a category you wish to create", example = "Electronics")
    private String categoryName;
}
