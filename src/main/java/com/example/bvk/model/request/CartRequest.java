package com.example.bvk.model.request;

import com.example.bvk.common.model.BaseRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CartRequest extends BaseRequest {

    @ApiModelProperty(name = "Transaksi ID", example = "Trx001")
    private String trxId;

    @ApiModelProperty(name = "Item ID", example = "1")
    private Long itemId;

    @ApiModelProperty(name = "Jumlah barang", example = "1")
    private Integer qty;

}
