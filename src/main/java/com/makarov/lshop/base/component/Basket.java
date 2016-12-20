package com.makarov.lshop.base.component;


import com.google.common.collect.Lists;
import com.makarov.lshop.base.model.ProductEntity;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Basket {

    private List<ProductEntity> basketProducts = Lists.newArrayList();

    public Basket() {
    }

    public List<ProductEntity> getBasketProducts() {
        if (this.basketProducts == null) {
            this.basketProducts = Lists.newArrayList();
        }
        return this.basketProducts;
    }

    public void setBasketProducts(List<ProductEntity> basketProducts) {
        this.basketProducts = basketProducts;
    }

    public void deleteProduct(long id) {
        Iterator<ProductEntity> iter = basketProducts.iterator();
        while (iter.hasNext()) {
            ProductEntity item = iter.next();
            if (item.getId() == id) {
                iter.remove();
                break;
            }
        }
    }
}
