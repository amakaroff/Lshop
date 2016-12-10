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

    @Override
    public ProfileEntity getProfile(String login) {
        return profileRepository.findByLogin(login);
    }

    @Override
    public void saveProfile(ProfileEntity profile) {
        profileRepository.save(profile);
    }

    @Override
    public List<ProductEntity> getProducts() {
        return Lists.newArrayList(productRepository.findAll());
    }
}
