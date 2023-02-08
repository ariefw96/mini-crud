package com.example.bvk.model.request;

import com.example.bvk.common.model.BaseRequest;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemRequest extends BaseRequest {

    private String itemName;
    private BigDecimal itemPrice;

}
