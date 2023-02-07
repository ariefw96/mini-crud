package com.example.bvk.service;

import com.example.bvk.model.entity.Cart;
import com.example.bvk.model.request.CartRequest;
import com.example.bvk.model.response.ValidationReponse;
import com.example.bvk.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class RemoveFromCartService {

    @Autowired
    CartRepository cartRepository;

    public ValidationReponse execute(CartRequest cartRequest){

        Optional <Cart> cart = cartRepository.getItemOnCart(cartRequest.getTrxId(), cartRequest.getItemId());
        if(ObjectUtils.isEmpty(cart.get())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Item not found on cart");
        }
        cartRepository.doDeleteItemOnCart(cartRequest.getTrxId(), cartRequest.getItemId());
        return ValidationReponse.builder()
                .result(Boolean.TRUE)
                .build();

    }


}
