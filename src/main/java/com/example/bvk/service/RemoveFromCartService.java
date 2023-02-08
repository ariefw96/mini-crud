package com.example.bvk.service;

import com.example.bvk.model.request.CartRequest;
import com.example.bvk.model.response.ValidationReponse;
import com.example.bvk.repository.CartRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class RemoveFromCartService {

    CartRepository cartRepository;

    public ValidationReponse execute(CartRequest cartRequest){

        cartRepository.getItemOnCart(cartRequest.getTrxId(), cartRequest.getItemId()).ifPresentOrElse(data -> {
            cartRepository.doDeleteItemOnCart(cartRequest.getTrxId(), cartRequest.getItemId());
        }, () -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Item not found on cart");
        });
        return ValidationReponse.builder()
                .result(Boolean.TRUE)
                .build();

    }


}
