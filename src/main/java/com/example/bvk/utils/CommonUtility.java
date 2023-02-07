package com.example.bvk.utils;

import com.example.bvk.model.entity.Item;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class CommonUtility {

    public void doCheckIsDeleted(Item item){
        if(!item.getIsActive()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Item already deleted");
        }
    }

}
