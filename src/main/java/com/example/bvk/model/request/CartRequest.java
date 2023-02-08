package com.example.bvk.model.request;

import com.example.bvk.common.model.BaseRequest;
import lombok.Data;

@Data
public class CartRequest extends BaseRequest {

    private String trxId;
    private Long itemId;
    private Integer qty;

}
