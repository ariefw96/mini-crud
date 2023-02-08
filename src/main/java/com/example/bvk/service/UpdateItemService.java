package com.example.bvk.service;

import com.example.bvk.common.service.BaseService;
import com.example.bvk.model.entity.Item;
import com.example.bvk.model.request.FindByIdRequest;
import com.example.bvk.model.request.UpdateItemRequest;
import com.example.bvk.model.response.ValidationReponse;
import com.example.bvk.repository.CartRepository;
import com.example.bvk.repository.ItemRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UpdateItemService implements BaseService<UpdateItemRequest, ValidationReponse> {

    private CartRepository cartRepository;
    private ItemRepository itemRepository;
    private GetItemByIdService getItemByIdService;

    public UpdateItemService(CartRepository cartRepository, ItemRepository itemRepository, GetItemByIdService getItemByIdService) {
        this.cartRepository = cartRepository;
        this.itemRepository = itemRepository;
        this.getItemByIdService = getItemByIdService;
    }

    @Override
    public ValidationReponse execute(UpdateItemRequest input) {
        this.doValidateRequest(input);

        if(cartRepository.checkItemsOnCart(input.getId()) > 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Item exist in cart, cannot perform update");
        }

        Item item = getItemByIdService.execute(FindByIdRequest.builder()
                        .id(input.getId())
                        .build()
        ).getContent();

        item.setItemName(input.getItemName());
        item.setPrice(input.getItemPrice());

        itemRepository.save(item);

        return ValidationReponse.builder()
                .result(Boolean.TRUE)
                .build();
    }

    private void doValidateRequest(UpdateItemRequest input){
        if(ObjectUtils.isEmpty(input.getId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Id cannot is required");
        }
        if(ObjectUtils.isEmpty(input.getItemName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Item Name is required");
        }
        if(ObjectUtils.isEmpty(input.getItemPrice())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Item Price is required");
        }
    }
}
