package com.example.bvk.controller;

import com.example.bvk.model.request.*;
import com.example.bvk.service.*;
import com.example.bvk.utils.Constant;
import com.example.bvk.common.model.RestResponse;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("api/v1/item")
@Slf4j
public class ItemController {

    private AddItemService addItemService;
    private GetAllItemService getAllItemService;
    private DeleteItemService deleteItemService;
    private GetItemByIdService getItemByIdService;
    private UpdateItemService updateItemService;

    public ItemController(AddItemService addItemService, GetAllItemService getAllItemService, DeleteItemService deleteItemService,
                          GetItemByIdService getItemByIdService, UpdateItemService updateItemService) {
        this.addItemService = addItemService;
        this.getAllItemService = getAllItemService;
        this.deleteItemService = deleteItemService;
        this.getItemByIdService = getItemByIdService;
        this.updateItemService = updateItemService;
    }

    @ApiOperation(value = "Get All Item",notes = "API to get all item")
    @GetMapping("/get")
    public ResponseEntity<RestResponse> getItem(
            SearchRequest searchRequest
    ){
        log.info("Search request = {}", searchRequest);
        RestResponse response = new RestResponse(
                getAllItemService.execute(SpecificationRequest.builder()
                                .specification(getItemSpec(searchRequest))
                                .pageable(getCommonPageable(searchRequest))
                        .build()),
                Constant.DATA_FOUND,
                true
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @ApiOperation(value = "Get Item by Id",notes = "API to get item by its Id")
    @GetMapping("/get/{id}")
    public ResponseEntity<RestResponse> getItemById(
            @PathVariable("id") Long id
    ){
        RestResponse response = new RestResponse(
                getItemByIdService.execute(
                        FindByIdRequest.builder()
                                .id(id)
                                .build()
                ),
                Constant.DATA_FOUND,
                true
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "Add New Item", notes = "API to add new item to database")
    @PostMapping("/add")
    public ResponseEntity<Object> postItem(
            @RequestBody ItemRequest itemRequest
   ){
        RestResponse response = new RestResponse(
                null,
                Constant.DATA_SUBMITTED,
                addItemService.execute(itemRequest).getResult()
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "Update Item by Id and request",notes = "API to update Item based on their Id")
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateItem(
            @PathVariable("id") Long id,
            @RequestBody UpdateItemRequest updateItemRequest
    ){
        updateItemRequest.setId(id);
        RestResponse response = new RestResponse(
                null,
                Constant.DATE_UPDATED,
                updateItemService.execute(updateItemRequest).getResult()
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete Item",notes = "API to delete item (soft deleted)")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteItem(
            @PathVariable("id") Long id
    ){
        RestResponse response = new RestResponse(
                null,
                Constant.DATA_DELETED,
                deleteItemService.execute(
                        FindByIdRequest.builder()
                                .id(id)
                                .build()
                ).getResult()
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private Specification getItemSpec(SearchRequest searchRequest){
        log.info("Request Spec Search = {}", searchRequest);
        return Specification.where((root, query, criteriaBuilder) -> {
            List<Predicate> predicate = new ArrayList<>();
            if(!StringUtils.isEmpty(searchRequest.getTextSearch())){
                List<javax.persistence.criteria.Predicate> predicateText = new ArrayList<>();
                predicateText.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("itemName").as(String.class)),
                        "%"+searchRequest.getTextSearch().toLowerCase()+"%"));
                predicate.add(criteriaBuilder.or(predicateText.toArray(new Predicate[]{})));
            }
            if(searchRequest.getIsActive()){
                predicate.add(criteriaBuilder.equal(root.get(Constant.IS_ACTIVE), searchRequest.getIsActive()));
            }
            return criteriaBuilder.and(predicate.toArray(new javax.persistence.criteria.Predicate[]{}));
        });
    }

    private Pageable getCommonPageable(SearchRequest searchRequest){
        log.info("Request Pagination = {}", searchRequest);
        int page = ObjectUtils.isEmpty(searchRequest.getPageNumber()) ? 0 : searchRequest.getPageNumber();
        int size = ObjectUtils.isEmpty(searchRequest.getPageSize()) ? Integer.MAX_VALUE : searchRequest.getPageSize();
        Sort.Direction sort = (!StringUtils.isEmpty(searchRequest.getSort()) && searchRequest.getSort().equalsIgnoreCase("ASC")) ? Sort.Direction.ASC : Sort.Direction.DESC;
        String sortBy = StringUtils.isEmpty(searchRequest.getSortBy()) ? "id" : searchRequest.getSortBy();
        return PageRequest.of(page, size, sort, sortBy);
    }

}
