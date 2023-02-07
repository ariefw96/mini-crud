package com.example.bvk.model.response;

import com.example.bvk.model.entity.Cart;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class CartResponse {

    private BigDecimal subTotalPrice;
    private String trxId;
    private List<Cart> items;

}
