package com.makarov.lshop.base.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by Алексей on 02.12.2016.
 */

@Entity
@Table(name = "products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //was IDENTITY
    @Column(name = "product_id")
    private BigInteger id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    //photo

    @Column(name = "price")
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "profile_id")
    private ProfileEntity profile;

    public ProductEntity() {
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
