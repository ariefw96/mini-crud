package com.example.bvk.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RestResponse {

    private Object data;
    private String message;
    private Boolean result;

}
