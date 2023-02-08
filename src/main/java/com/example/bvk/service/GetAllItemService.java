package com.example.bvk.service;

import com.example.bvk.model.entity.Item;
import com.example.bvk.model.request.SpecificationRequest;
import com.example.bvk.model.response.ItemListReponse;
import com.example.bvk.repository.ItemRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class GetAllItemService {

    ItemRepository itemRepository;

    public GetAllItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public ItemListReponse execute(SpecificationRequest input){
        Page<Item> itemList = itemRepository.findAll(input.getSpecification(), input.getPageable());
        return ItemListReponse.builder()
                .page(itemList)
                .build();
    }

    private List<Item> doFilterIsActive(List<Item> listItem){
        return listItem.stream().filter(Item::getIsActive).collect(Collectors.toList());
    }

}
