package com.example.bvk.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FindByIdRequest {

    Long id;

}
