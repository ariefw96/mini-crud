package com.example.bvk.model.response;

import com.example.bvk.model.entity.Item;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemResponse {

    private Item content;
}
