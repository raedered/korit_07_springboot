package com.example.shoppinglist.service;

import com.example.shoppinglist.domain.Shopping;
import com.example.shoppinglist.domain.ShoppingRepository;
import com.example.shoppinglist.domain.User;
import com.example.shoppinglist.domain.UserRepository;
import com.example.shoppinglist.dto.ShoppingRequestDto;
import jakarta.persistence.EntityNotFoundException;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppingService {

    private final UserRepository userRepository;
    private final ShoppingRepository shoppingRepository;

    public ShoppingService(UserRepository userRepository, ShoppingRepository shoppingRepository) {
        this.userRepository = userRepository;
        this.shoppingRepository = shoppingRepository;
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("User를 찾을수 없습니다."));
    }

    // 전체 불러오기
    @Transactional
    public List<Shopping> getShoppings() {
        return shoppingRepository.findAll();
    }

    // 유저별 리스트 불러오기
    @Transactional(readOnly = true)
    public List<Shopping> getShoppingsForcurrentUser() {
        User currentUser = getCurrentUser();
        return shoppingRepository.findByUserId(currentUser.getId());
    }

    // 리스트 추가
    @Transactional
    public Shopping createShopping(ShoppingRequestDto shoppingRequestDto) {
        User currentUser = getCurrentUser();
        Shopping newShopping = new Shopping(shoppingRequestDto.getProduct(), shoppingRequestDto.getAmount(), currentUser);

        return shoppingRepository.save(newShopping);
    }

    // 리스트 수정
//    @Transactional
//    public Shopping updateShopping(Long id, ShoppingRequestDto updateDto) throws AccessDeniedException {
//        Shopping shopping = shoppingRepository.findById(id);
//
//        shopping.setProduct(updateDto.getProduct());
//        shopping.setAmount(updateDto.getAmount());
//
//        return shoppingRepository.save(shopping);
//    }

}
