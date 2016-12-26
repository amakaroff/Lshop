package com.makarov.lshop.base.component;


import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import com.makarov.lshop.base.model.ProductEntity;
import com.makarov.lshop.base.model.ProfileEntity;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Basket {

    private List<ProductEntity> basketProducts = Lists.newArrayList();

    private boolean isLogin = false;

    private ProfileEntity profile;

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

    public void deleteProduct(final long id) {
        basketProducts.remove(FluentIterable.from(basketProducts).filter(new Predicate<ProductEntity>() {
            @Override
            public boolean apply(ProductEntity productEntity) {
                return productEntity.getId().equals(id);
            }
        }).first().get());
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setIsLogin(boolean isLoggin) {
        this.isLogin = isLoggin;
    }

    public ProfileEntity getProfile() {
        return profile;
    }

    public void setProfile(ProfileEntity profile) {
        this.profile = profile;
    }
}
