package com.example.bvk.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RestResponse {

    private Object data;
    private String message;
    private Boolean result;

}
