package com.makarov.lshop.base.service.product.impl;

import com.makarov.lshop.base.service.product.api.Validator;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ProductValidator implements Validator {

    @Override
    public boolean nameValidate(String name) {
        return !(name.length() < 2 || name.length() > 30);
    }

    @Override
    public boolean priceValidate(String price) {
        Pattern pattern = Pattern.compile("^[1-9]+$");
        Matcher matcher = pattern.matcher(price);
        return matcher.matches();
    }

    @Override
    public boolean descriptionValidate(String description) {
        return !(description.length() < 0);
    }

    @Override
    public boolean photoValidate(String urlPhoto) {
        Pattern pattern = Pattern.compile("^(.*\\.(?:png|jpg))$");
        Matcher matcher = pattern.matcher(urlPhoto);
        return matcher.matches();
    }
}
