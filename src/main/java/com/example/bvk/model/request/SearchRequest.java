package com.example.bvk.model.request;

import com.example.bvk.common.model.BaseRequest;
import lombok.Data;

@Data
public class SearchRequest extends BaseRequest {
    private Boolean isActive = Boolean.FALSE;
    private String textSearch;
    private Integer pageNumber;
    private Integer pageSize;
    private Boolean isAscending = Boolean.FALSE;
}
