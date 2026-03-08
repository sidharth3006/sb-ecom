package com.ecommerce.project.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDTO {
    private Long cartItemId;
    @JsonIgnore
    private CartDTO cart;
    private ProductDTO productDTO;
    private Integer quantity;
    private Double discount;
    private Double itemPrice;
}
