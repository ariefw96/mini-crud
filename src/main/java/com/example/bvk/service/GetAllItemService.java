package com.example.bvk.service;

import com.example.bvk.common.service.BaseService;
import com.example.bvk.model.entity.Item;
import com.example.bvk.model.request.SpecificationRequest;
import com.example.bvk.model.response.ItemListResponse;
import com.example.bvk.repository.ItemRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class GetAllItemService implements BaseService<SpecificationRequest, ItemListResponse> {

    ItemRepository itemRepository;

    public GetAllItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public ItemListResponse execute(SpecificationRequest input){
        Page<Item> itemList = itemRepository.findAll(input.getSpecification(), input.getPageable());
        return ItemListResponse.builder()
                .page(itemList)
                .build();
    }

    private List<Item> doFilterIsActive(List<Item> listItem){
        return listItem.stream().filter(Item::getIsActive).collect(Collectors.toList());
    }

}
