package com.example.bvk.model.response;

import com.example.bvk.common.model.BaseResponse;
import com.example.bvk.model.entity.Item;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemResponse extends BaseResponse {

    private Item content;
}
