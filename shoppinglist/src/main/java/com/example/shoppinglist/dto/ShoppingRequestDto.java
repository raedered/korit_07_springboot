package com.example.shoppinglist.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShoppingRequestDto {
    private String product;
    private int amount;
}
