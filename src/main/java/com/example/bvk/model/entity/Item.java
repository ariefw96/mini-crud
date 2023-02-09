package com.example.bvk.model.entity;

import com.example.bvk.common.model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "item")
@JsonPropertyOrder({"id","itemName","price","isActive","createdDate","updatedDate"})
public class Item extends BaseEntity {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "is_active")
    private Boolean isActive;
}
