package com.example.bvk.controller;

import com.example.bvk.model.request.FindByIdRequest;
import com.example.bvk.model.request.ItemRequest;
import com.example.bvk.model.request.SearchRequest;
import com.example.bvk.model.request.SpecificationRequest;
import com.example.bvk.service.DeleteItemService;
import com.example.bvk.service.GetAllItemService;
import com.example.bvk.service.GetItemByIdService;
import com.example.bvk.service.AddItemService;
import com.example.bvk.utils.Constant;
import com.example.bvk.common.model.RestResponse;
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

    public ItemController(AddItemService addItemService, GetAllItemService getAllItemService, DeleteItemService deleteItemService, GetItemByIdService getItemByIdService) {
        this.addItemService = addItemService;
        this.getAllItemService = getAllItemService;
        this.deleteItemService = deleteItemService;
        this.getItemByIdService = getItemByIdService;
    }

    @GetMapping("/get")
    public ResponseEntity<RestResponse> getItem(
            SearchRequest searchRequest
    ){
        RestResponse response = new RestResponse(
                getAllItemService.execute(SpecificationRequest.builder()
                                .specification(getItemSpec(searchRequest))
                                .pageable(getCommonPageable(searchRequest))
                                .isActive(searchRequest.getIsActive())
                        .build()),
                Constant.DATA_FOUND,
                true
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

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

    @PostMapping("/post")
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

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateItem(
            @PathVariable("id") Long id
    ){
        return ResponseEntity.ok().body("This feature under development");
    }

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
        log.info("Request = {}", searchRequest);
        return Specification.where((root, query, criteriaBuilder) -> {
            List<Predicate> predicate = new ArrayList<>();
            if(!StringUtils.isEmpty(searchRequest.getTextSearch())){
                log.info("Text search?");
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
        int page = ObjectUtils.isEmpty(searchRequest.getPageNumber()) ? 0 : searchRequest.getPageNumber();
        int size = ObjectUtils.isEmpty(searchRequest.getPageSize()) ? Integer.MAX_VALUE : searchRequest.getPageSize();
        Sort.Direction sort = searchRequest.getIsAscending() ? Sort.Direction.ASC : Sort.Direction.DESC;
        String sortBy = "id";
        return PageRequest.of(page, size, sort, sortBy);
    }

}
