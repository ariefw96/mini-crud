package com.example.bvk.model.response;

import com.example.bvk.model.entity.Item;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class ItemListReponse {

    List<Item> content;

}
