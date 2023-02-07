package com.example.bvk.service;

import com.example.bvk.model.entity.Item;
import com.example.bvk.model.request.FindByIdRequest;
import com.example.bvk.model.response.ValidationReponse;
import com.example.bvk.repository.CartRepository;
import com.example.bvk.repository.ItemRepository;
import com.example.bvk.utils.CommonUtility;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DeleteItemService {

    ItemRepository itemRepository;
    GetItemByIdService getItemByIdService;
    CommonUtility commonUtility;
    CartRepository cartRepository;

    public DeleteItemService(ItemRepository itemRepository, GetItemByIdService getItemByIdService, CommonUtility commonUtility, CartRepository cartRepository) {
        this.itemRepository = itemRepository;
        this.getItemByIdService = getItemByIdService;
        this.commonUtility = commonUtility;
        this.cartRepository = cartRepository;
    }

    public ValidationReponse execute(FindByIdRequest input){

        Item item = getItemByIdService.execute(input).getContent();
        this.doValidateIsDelete(item);
        cartRepository.checkItemsOnCart(input.getId()).ifPresent(data -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Item exist in cart");
        });
        item.setIsActive(Boolean.FALSE);
        itemRepository.save(item);

        return ValidationReponse.builder()
                .result(Boolean.TRUE)
                .build();
    }

    private void doValidateIsDelete(Item item){
        commonUtility.doCheckIsDeleted(item);
    }

}
