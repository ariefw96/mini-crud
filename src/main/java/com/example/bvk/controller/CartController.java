package com.example.bvk.controller;

import com.example.bvk.model.request.CartRequest;
import com.example.bvk.model.request.FindByTrxIdRequest;
import com.example.bvk.service.AddToCartService;
import com.example.bvk.service.GetCartByTrxId;
import com.example.bvk.service.RemoveFromCartService;
import com.example.bvk.utils.Constant;
import com.example.bvk.common.model.RestResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/cart")
public class CartController {

    AddToCartService addToCartService;
    GetCartByTrxId getCartByTrxId;
    RemoveFromCartService removeFromCartService;

    public CartController(AddToCartService addToCartService, GetCartByTrxId getCartByTrxId, RemoveFromCartService removeFromCartService) {
        this.addToCartService = addToCartService;
        this.getCartByTrxId = getCartByTrxId;
        this.removeFromCartService = removeFromCartService;
    }

    @ApiOperation(value = "Get Cart by its TrxId",notes = "Get list items on cart based on transcationId")
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

    @ApiOperation(value = "Add item to cart",notes = "API to add item to cart based on trxId")
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

    @ApiOperation(value = "Remove item from cart",notes = "API to remove item on cart based on item id and Transaction Id")
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
