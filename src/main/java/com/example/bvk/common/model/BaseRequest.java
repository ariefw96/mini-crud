package com.example.bvk.common.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(
        ignoreUnknown = true
)

public class BaseRequest implements Serializable {

    public BaseRequest() {
    }
}
