package com.makarov.lshop.base.dao.api.repository;

import com.makarov.lshop.base.model.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

public interface ProductRepository extends CrudRepository<ProductEntity, BigInteger> {
}