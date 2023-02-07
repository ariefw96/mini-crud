package com.example.bvk.service;


import com.example.bvk.model.entity.Cart;
import com.example.bvk.model.entity.Item;
import com.example.bvk.model.request.CartRequest;
import com.example.bvk.model.response.ValidationReponse;
import com.example.bvk.repository.CartRepository;
import com.example.bvk.repository.ItemRepository;
import com.example.bvk.utils.CommonUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Service
public class AddToCartService {

    CartRepository cartRepository;
    ItemRepository itemRepository;
    CommonUtility commonUtility;

    public AddToCartService(CartRepository cartRepository, ItemRepository itemRepository, CommonUtility commonUtility) {
        this.cartRepository = cartRepository;
        this.itemRepository = itemRepository;
        this.commonUtility = commonUtility;
    }

    public ValidationReponse execute(CartRequest cartRequest){
        this.doValidateCart(cartRequest);
        AtomicReference<Item> itemAtomicReference = new AtomicReference<>();
        itemRepository.findById(cartRequest.getItemId()).ifPresentOrElse(data -> {
                    commonUtility.doCheckIsDeleted(data);
                    itemAtomicReference.set(data);
                },
                () -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found");
                }
        );
        Cart cart = cartRepository.getItemOnCart(cartRequest.getTrxId(), cartRequest.getItemId()).orElse(new Cart());
        log.info("Cart = {}", cart);
        if(ObjectUtils.isEmpty(cart.getTrxId())){
            cart.setItem(itemAtomicReference.get());
            cart.setTrxId(cartRequest.getTrxId());
            cart.setQty(cartRequest.getQty());
        }else{
            cart.setQty(cart.getQty() + cartRequest.getQty());
        }

        cartRepository.save(cart);

        return ValidationReponse.builder()
                .result(Boolean.TRUE)
                .build();
    }

    public void doValidateCart(CartRequest input){
        if(ObjectUtils.isEmpty(input.getTrxId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Trx Id cannot be empty");
        }
        if(ObjectUtils.isEmpty(input.getItemId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Item Id cannot be empty");
        }
        if(ObjectUtils.isEmpty(input.getQty())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Quantity cannot be empty");
        }
    }

}
