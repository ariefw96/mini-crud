package com.example.bvk.controller;

import com.example.bvk.model.request.FindByIdRequest;
import com.example.bvk.model.request.ItemRequest;
import com.example.bvk.model.request.SearchRequest;
import com.example.bvk.service.DeleteItemService;
import com.example.bvk.service.GetAllItemService;
import com.example.bvk.service.GetItemByIdService;
import com.example.bvk.service.SaveItemService;
import com.example.bvk.utils.Constant;
import com.example.bvk.utils.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/item")
public class ItemController {


    @Autowired
    private SaveItemService saveItemService;

    @Autowired
    private GetAllItemService getAllItemService;

    @Autowired
    private DeleteItemService deleteItemService;

    @Autowired
    private GetItemByIdService getItemByIdService;

    @GetMapping("/get")
    public ResponseEntity<RestResponse> getItem(
            SearchRequest searchRequest
    ){
        RestResponse response = new RestResponse(
                getAllItemService.execute(searchRequest),
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
                saveItemService.saveItem(itemRequest).getResult()
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

}
