package com.example.bvk.model.request;

import lombok.Data;

@Data
public class CartRequest {

    private String trxId;
    private Long itemId;
    private Integer qty;

}
