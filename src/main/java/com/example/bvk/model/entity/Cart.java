package com.example.bvk.model.entity;

import com.example.bvk.common.model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "cart")
@JsonPropertyOrder({"trxId","item","qty","createdDate","updatedDate"})
public class Cart extends BaseEntity {

    @Id
    @JsonIgnore
    @Column(name = "id")
    private Long id;

    @JsonIgnore
    @Column(name = "trx_id")
    private String trxId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(name = "qty")
    private Integer qty;


}
