package com.example.bvk.model.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;


@Builder
@Data
public class ItemListReponse {

    private Page page;

}
