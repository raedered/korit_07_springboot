package com.example.shoppinglist.web;

import com.example.shoppinglist.domain.Shopping;
import com.example.shoppinglist.dto.ShoppingRequestDto;
import com.example.shoppinglist.service.ShoppingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shopping")
public class ShoppingController {

    private final ShoppingService shoppingService;

    public ShoppingController(ShoppingService shoppingService) {
        this.shoppingService = shoppingService;
    }

    // 전체 값 불러오기
    @GetMapping
    public ResponseEntity<List<Shopping>> getShopping() {
        return ResponseEntity.ok(shoppingService.getShoppings());
    }

    // 새로추가
    @PostMapping
    public ResponseEntity<Shopping> createShopping(@RequestBody ShoppingRequestDto requestDto) {
        Shopping createdShopping = shoppingService.createShopping(requestDto);
        return new ResponseEntity<>(createdShopping, HttpStatus.CREATED);
    }
}
