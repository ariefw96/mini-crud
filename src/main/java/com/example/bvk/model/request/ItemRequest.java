package com.example.bvk.model.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemRequest {

    private String itemName;
    private BigDecimal itemPrice;

}
