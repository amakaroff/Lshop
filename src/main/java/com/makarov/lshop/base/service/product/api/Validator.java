package com.makarov.lshop.base.service.product.api;


public interface Validator {

    boolean nameValidate(String name);

    boolean priceValidate(String price);

    boolean descriptionValidate(String description);

    boolean photoValidate(String urlPhoto);
}
