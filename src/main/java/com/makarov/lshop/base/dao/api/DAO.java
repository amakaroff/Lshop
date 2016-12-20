package com.makarov.lshop.base.dao.api;

import com.makarov.lshop.base.model.ProductEntity;
import com.makarov.lshop.base.model.ProfileEntity;

import java.util.List;

public interface DAO {

    ProfileEntity getProfile(String login);

    void saveProfile(ProfileEntity profile);

    List<ProductEntity> getProducts();

    ProductEntity findById(Long id);

    List<ProductEntity> findByCategory(String category);
}
