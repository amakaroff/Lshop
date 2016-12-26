package com.makarov.lshop.base.dao.api;

import com.makarov.lshop.base.model.ProductEntity;
import com.makarov.lshop.base.model.ProfileEntity;

import java.util.List;

public interface DAO {

    ProfileEntity getProfile(String login);

    ProfileEntity getProfile(Long id);

    void saveProfile(ProfileEntity profile);

    List<ProductEntity> getProducts(String category, boolean doSorted);

    ProductEntity findById(Long id);

    void deleteProducts(List<ProductEntity> products);

    List<ProfileEntity> findByProfileId(Long id);

    void saveProduct(ProductEntity product);
}
