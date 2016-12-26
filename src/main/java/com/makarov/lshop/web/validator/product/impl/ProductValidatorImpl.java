package com.makarov.lshop.web.validator.product.impl;

import com.makarov.lshop.base.model.ProductEntity;
import com.makarov.lshop.base.service.product.api.Validator;
import com.makarov.lshop.web.validator.product.api.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductValidatorImpl implements ProductValidator {

    @Autowired
    private Validator validator;

    @Override
    public String validateProduct(ProductEntity productEntity) {
        if (!validator.nameValidate(productEntity.getName())) {
            productEntity.setName("");
            return "invalid name";
        }

        if (!validator.priceValidate(productEntity.getPrice())) {
            productEntity.setPrice("");
            return "invalid price";
        }

        if (!validator.photoValidate(productEntity.getUrlPhoto())) {
            productEntity.setUrlPhoto("");
            return "invalid url photo";
        }
        if (!validator.descriptionValidate(productEntity.getDescription())) {
            productEntity.setDescription("");
            return "invalid description";
        }

        return "";
    }
}
