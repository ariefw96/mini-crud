package com.example.bvk.model.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FindByTrxIdRequest {

    public String trxId;

}
