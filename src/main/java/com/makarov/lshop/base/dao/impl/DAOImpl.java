package com.makarov.lshop.base.dao.impl;

import com.google.common.collect.Lists;
import com.makarov.lshop.base.dao.api.DAO;
import com.makarov.lshop.base.dao.api.repository.ProductRepository;
import com.makarov.lshop.base.dao.api.repository.ProfileRepository;
import com.makarov.lshop.base.model.ProductEntity;
import com.makarov.lshop.base.model.ProfileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DAOImpl implements DAO {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ProductRepository productRepository;

    private boolean isSorted = false;

    @Override
    public ProfileEntity getProfile(String login) {
        return profileRepository.findByLogin(login);
    }

    @Override
    public ProfileEntity getProfile(Long id) {
        return profileRepository.findOne(id);
    }

    @Override
    public void saveProfile(ProfileEntity profile) {
        profileRepository.save(profile);
    }

    @Override
    public List<ProductEntity> getProducts(String category, boolean doSorted) {
        if (category != null && !category.equals("All")) {
            if (doSorted) {
                if (isSorted) {
                    isSorted = false;
                    return productRepository.findByCategoryOrderByPriceAsc(category);
                } else {
                    isSorted = true;
                    return productRepository.findByCategoryOrderByPriceDesc(category);
                }
            } else {
                return productRepository.findByCategory(category);
            }
        } else {
            if (doSorted) {
                if (isSorted) {
                    isSorted = false;
                    return productRepository.findAllByOrderByPriceAsc();
                } else {
                    isSorted = true;
                    return productRepository.findAllByOrderByPriceDesc();
                }
            } else {
                return Lists.newArrayList(productRepository.findAll());
            }
        }
    }

    @Override
    public ProductEntity findById(Long id) {
        return productRepository.findOne(id);
    }

    @Override
    public void deleteProducts(List<ProductEntity> products) {
        for (ProductEntity product : products) {
            productRepository.delete(product);
        }
    }

    @Override
    public List<ProfileEntity> findByProfileId(Long id) {
        return productRepository.findByProfile_Id(id);
    }

    @Override
    public void saveProduct(ProductEntity product) {
        productRepository.save(product);
    }
}
