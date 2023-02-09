package com.example.bvk.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UpdateItemRequest extends ItemRequest{

    @JsonIgnore
    private Long id;

}
