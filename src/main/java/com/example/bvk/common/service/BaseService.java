package com.example.bvk.common.service;


import com.example.bvk.common.model.BaseRequest;
import com.example.bvk.common.model.BaseResponse;

public interface BaseService <T extends BaseRequest, V extends BaseResponse>{
    V execute (T input);
}
