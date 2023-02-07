package com.example.bvk.service;

import com.example.bvk.model.entity.Item;
import com.example.bvk.model.request.SearchRequest;
import com.example.bvk.model.response.ItemListReponse;
import com.example.bvk.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class GetAllItemService {

    @Autowired
    ItemRepository itemRepository;

    public GetAllItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public ItemListReponse execute(SearchRequest searchRequest){
        List<Item> itemList = itemRepository.findAll();
        return ItemListReponse.builder()
                .content(searchRequest.getIsActive()? this.doFilterIsActive(itemList) : itemList)
                .build();
    }

    private List<Item> doFilterIsActive(List<Item> listItem){
        return listItem.stream().filter(Item::getIsActive).collect(Collectors.toList());
    }

}
