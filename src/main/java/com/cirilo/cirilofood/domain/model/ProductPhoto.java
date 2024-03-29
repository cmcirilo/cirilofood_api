package com.cirilo.cirilofood.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class ProductPhoto {

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "product_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Product product;

    private String fileName;

    private String description;

    private String contentType;

    private Long size;

    public Long getRestaurantId(){
        if (getProduct() != null){
            return getProduct().getRestaurant().getId();
        }
        return null;
    }
}
