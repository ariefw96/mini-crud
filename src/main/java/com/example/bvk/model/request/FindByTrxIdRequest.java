package com.example.bvk.model.request;

import com.example.bvk.common.model.BaseRequest;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FindByTrxIdRequest extends BaseRequest {

    public String trxId;

}
