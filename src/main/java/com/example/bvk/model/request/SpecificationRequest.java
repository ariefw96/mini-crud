package com.example.bvk.model.request;


import com.example.bvk.common.model.BaseRequest;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

@Data
@Builder
public class SpecificationRequest extends BaseRequest {

    private Specification specification;
    private Pageable pageable;
    private Boolean isActive = Boolean.FALSE;
}
