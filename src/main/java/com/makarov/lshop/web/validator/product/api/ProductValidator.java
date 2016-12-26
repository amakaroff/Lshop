package com.makarov.lshop.web.validator.product.api;

import com.makarov.lshop.base.model.ProductEntity;

/**
 * Created by Алексей on 26.12.2016.
 */
public interface ProductValidator {

    String validateProduct(ProductEntity productEntity);
}
