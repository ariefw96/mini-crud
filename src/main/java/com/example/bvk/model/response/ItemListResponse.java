package com.example.bvk.model.response;

import com.example.bvk.common.model.BaseResponse;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;


@Builder
@Data
public class ItemListResponse extends BaseResponse {

    private Page page;

}
