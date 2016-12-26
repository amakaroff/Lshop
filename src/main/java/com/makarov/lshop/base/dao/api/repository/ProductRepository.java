package com.makarov.lshop.base.dao.api.repository;

import com.makarov.lshop.base.model.ProductEntity;
import com.makarov.lshop.base.model.ProfileEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<ProductEntity, Long> {

    List<ProductEntity> findByCategory(String category);

    List<ProductEntity> findAllByOrderByPriceAsc();

    List<ProductEntity> findAllByOrderByPriceDesc();

    List<ProductEntity> findByCategoryOrderByPriceAsc(String category);

    List<ProductEntity> findByCategoryOrderByPriceDesc(String category);

    List<ProfileEntity> findByProfile_Id(Long id);
}
