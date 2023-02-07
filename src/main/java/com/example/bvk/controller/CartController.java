package com.example.bvk.controller;

import com.example.bvk.model.entity.Cart;
import com.example.bvk.model.request.CartRequest;
import com.example.bvk.model.request.FindByTrxIdRequest;
import com.example.bvk.repository.CartRepository;
import com.example.bvk.service.AddToCartService;
import com.example.bvk.service.GetCartByTrxId;
import com.example.bvk.service.RemoveFromCartService;
import com.example.bvk.utils.Constant;
import com.example.bvk.utils.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/cart")
public class CartController {

    @Autowired
    AddToCartService addToCartService;

    @Autowired
    GetCartByTrxId getCartByTrxId;

    @Autowired
    RemoveFromCartService removeFromCartService;

    @GetMapping("/get/{trxId}")
    public ResponseEntity<Object> getCart(
            @PathVariable("trxId") String trxId
    ){
        RestResponse response = new RestResponse(getCartByTrxId.execute(
                FindByTrxIdRequest.builder()
                        .trxId(trxId)
                        .build()
        ),Constant.DATA_FOUND, true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addToCart(
            @RequestBody CartRequest cartRequest
    ){
        RestResponse response = new RestResponse(
                null,
                Constant.DATA_SUBMITTED,
                addToCartService.execute(cartRequest).getResult()
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/remove")
    public ResponseEntity<Object> removeFromCart(
            @RequestBody CartRequest cartRequest
    ){
        RestResponse response = new RestResponse(
                null,
                Constant.DATA_SUBMITTED,
                removeFromCartService.execute(cartRequest).getResult()
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
