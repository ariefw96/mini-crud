package com.example.bvk.model.response;

import com.example.bvk.common.model.BaseResponse;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ValidationReponse extends BaseResponse {

    Boolean result;
}
