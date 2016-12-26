package com.makarov.lshop.web.controller;

import com.makarov.lshop.base.component.Basket;
import com.makarov.lshop.base.dao.api.DAO;
import com.makarov.lshop.base.model.ProductEntity;
import com.makarov.lshop.web.validator.product.api.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

@Controller
public class AddProductController {

    @Autowired
    private Basket basket;

    @Autowired
    private DAO dao;

    @Autowired
    private ProductValidator validator;


    @RequestMapping(value = "/newproduct", method = RequestMethod.POST)
    public String newProduct(ModelMap model) {
        ProductEntity productEntity = new ProductEntity();

        //model.addAttribute("")
        model.addAttribute("error", false);
        model.addAttribute("product", productEntity);
        return "addProduct";
    }

    @RequestMapping(value = "/addproduct", method = RequestMethod.POST)
    public String completeAddProduct(@ModelAttribute("product") ProductEntity product, ModelMap model) {
        trimAllFields(product);
        String message = validator.validateProduct(product);

        if (!"".equals(message) || product.getPrice() == null) {
            model.addAttribute("errorMessage", message);
            return "addProduct";
        }

        if (basket.getProfile().getProducts() == null) {
            basket.getProfile().setProducts(new ArrayList<ProductEntity>());
        }

        basket.getProfile().getProducts().add(product);
        product.setProfile(basket.getProfile());
        dao.saveProduct(product);
        return "redirect:/";
    }

    private void trimAllFields(ProductEntity product) {
        product.setName(product.getName().trim());
        product.setPrice(product.getPrice().trim());
        product.setUrlPhoto(product.getUrlPhoto().trim());
        product.setDescription(product.getDescription().trim());
    }
}
