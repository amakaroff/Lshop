package com.makarov.lshop.base.dao.impl;

import com.makarov.lshop.base.dao.api.DAO;
import com.makarov.lshop.base.dao.api.repository.ProductRepository;
import com.makarov.lshop.base.dao.api.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DAOImpl implements DAO {

    private ProfileRepository profileRepository;

    private ProductRepository productRepository;


}
