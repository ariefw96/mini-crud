package com.example.bvk.model.request;


import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

@Data
@Builder
public class SpecificationRequest {

    private Specification specification;
    private Pageable pageable;
    private Boolean isActive = Boolean.FALSE;
}
