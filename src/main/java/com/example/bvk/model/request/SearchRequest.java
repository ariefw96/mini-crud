package com.example.bvk.model.request;

import lombok.Data;

@Data
public class SearchRequest {
    private Boolean isActive = Boolean.FALSE;
    private String textSearch;
    private Integer pageNumber;
    private Integer pageSize;
    private Boolean isAscending = Boolean.FALSE;
}
