package com.ecommerce.project.config;

import com.ecommerce.project.model.CartItem;
import com.ecommerce.project.payload.CartItemDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.ModelMapper;

@Configuration
public class AppConfig {
    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();

        // Explicit mapping for CartItem → CartItemDTO
        modelMapper.typeMap(CartItem.class, CartItemDTO.class)
                .addMappings(mapper -> mapper.map(CartItem::getProductPrice, CartItemDTO::setItemPrice));

        return modelMapper;
    }
}
