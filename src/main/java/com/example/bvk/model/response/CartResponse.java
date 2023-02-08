package com.example.bvk.model.response;

import com.example.bvk.common.model.BaseResponse;
import com.example.bvk.model.entity.Cart;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class CartResponse extends BaseResponse {

    private BigDecimal subTotalPrice;
    private String trxId;
    private List<Cart> items;

}
