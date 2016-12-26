package com.makarov.lshop.web.controller;

import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.makarov.lshop.base.component.Basket;
import com.makarov.lshop.base.dao.api.DAO;
import com.makarov.lshop.base.model.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProductController {

    @Autowired
    private DAO dao;

    @Autowired
    private Basket basket;

    @RequestMapping(value = "/product{id}", method = RequestMethod.GET)
    public String getProduct(@PathVariable final Long id, ModelMap model) {
        ProductEntity productEntity = FluentIterable.from(dao.getProducts(null, false)).filter(new Predicate<ProductEntity>() {
            @Override
            public boolean apply(ProductEntity productEntity) {
                return !basket.getBasketProducts().contains(productEntity);
            }
        }).filter(new Predicate<ProductEntity>() {
            @Override
            public boolean apply(ProductEntity productEntity) {
                return productEntity.getId().equals(id);
            }
        }).first().orNull();
        if (productEntity != null) {
            model.addAttribute("customerId", productEntity.getProfile().getId());
            model.addAttribute("product", productEntity);
            return "product";
        } else {
            return "redirect:/";
        }
    }
}
