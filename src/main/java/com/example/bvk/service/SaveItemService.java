package com.example.bvk.service;

import com.example.bvk.model.entity.Item;
import com.example.bvk.model.request.ItemRequest;
import com.example.bvk.model.response.ValidationReponse;
import com.example.bvk.repository.ItemRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.server.ResponseStatusException;

@Service
public class SaveItemService {

    private ItemRepository itemRepository;

    public SaveItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public ValidationReponse saveItem(ItemRequest input){
        this.doValidateRequest(input);

        Item item = new Item();
        this.setId(item);
        item.setItemName(input.getItemName());
        item.setPrice(input.getItemPrice());
        item.setIsActive(Boolean.TRUE);

        itemRepository.save(item);

        return ValidationReponse.builder()
                .result(Boolean.TRUE)
                .build();
    }

    private void doValidateRequest(ItemRequest input){
        if(ObjectUtils.isEmpty(input.getItemName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Item Name cannot be null");
        }

        if(ObjectUtils.isEmpty(input.getItemPrice())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Item Price cannot be null");
        }
    }

    private void setId(Item item){
        Long id = itemRepository.selectMaxId();
        if(ObjectUtils.isEmpty(id)){
            item.setId(1L);
        }else{
            item.setId(id+1);
        }

    }
}
