package com.example.bvk.model.request;

import com.example.bvk.common.model.BaseRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemRequest extends BaseRequest {

    @ApiModelProperty(name = "Item Name", example = "Pop Ice Taro")
    private String itemName;

    @ApiModelProperty(name = "Item Price", example = "5000")
    private BigDecimal itemPrice;

}
