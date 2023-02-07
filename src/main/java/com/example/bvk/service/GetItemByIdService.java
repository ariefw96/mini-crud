package com.example.bvk.service;

import com.example.bvk.model.entity.Item;
import com.example.bvk.model.request.FindByIdRequest;
import com.example.bvk.model.response.ItemResponse;
import com.example.bvk.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.server.ResponseStatusException;

@Service
public class GetItemByIdService {

    @Autowired
    private ItemRepository itemRepository;

    public ItemResponse execute(FindByIdRequest input){
        this.doValidateInput(input);
        return ItemResponse.builder()
                .content(itemRepository.findById(input.getId()).orElseThrow(() -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found");
                }))
                .build();
    }

    public void doValidateInput(FindByIdRequest input){
        if(ObjectUtils.isEmpty(input.getId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id cannot be empty");
        }
    }

}
