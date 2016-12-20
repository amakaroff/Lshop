package com.makarov.lshop.base.cache.impl;

import com.google.common.collect.Lists;
import com.makarov.lshop.base.cache.api.Cache;
import com.makarov.lshop.base.dao.api.DAO;
import com.makarov.lshop.base.model.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;


@Component
public class ProductsCache implements Cache {

    private List<ProductEntity> currentProducts;

    @Autowired
    private DAO dao;

    private boolean isSorted = false;

    public List<ProductEntity> getCurrentProducts() {
        if (currentProducts == null) {
            currentProducts = dao.getProducts();
        }
        return Lists.newArrayList(currentProducts);
    }

    public void setCurrentProducts(List<ProductEntity> currentProducts) {
        this.currentProducts = currentProducts;
    }

    @Override
    public void setCategory(String category) {
        if (category.equals("All")) {
            setCurrentProducts(dao.getProducts());
        } else {
            setCurrentProducts(dao.findByCategory(category));
        }
        isSorted = false;
    }

    @Override
    public void sortByPrice() {
        if (!isSorted) {
            currentProducts.sort(new Comparator<ProductEntity>() {
                @Override
                public int compare(ProductEntity first, ProductEntity second) {
                    return first.getPrice().compareTo(second.getPrice());
                }
            });

            isSorted = true;
        } else {
            currentProducts.sort(new Comparator<ProductEntity>() {
                @Override
                public int compare(ProductEntity first, ProductEntity second) {
                    return second.getPrice().compareTo(first.getPrice());
                }
            });

            isSorted = false;
        }
    }
}
