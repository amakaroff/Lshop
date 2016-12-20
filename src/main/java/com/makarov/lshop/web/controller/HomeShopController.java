package com.makarov.lshop.web.controller;

import com.makarov.lshop.base.cache.impl.ProductsCache;
import com.makarov.lshop.base.component.Basket;
import com.makarov.lshop.base.dao.api.DAO;
import com.makarov.lshop.base.model.ProductEntity;
import com.makarov.lshop.base.model.ProfileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class HomeShopController {

    @Autowired
    private ProductsCache cache;

    @Autowired
    private DAO dao;

    @Autowired
    private Basket basket;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap model) {
        model.addAttribute("profileEntity", new ProfileEntity());
        List<ProductEntity> list = cache.getCurrentProducts();
        list.removeAll(basket.getBasketProducts());
        model.addAttribute("products", list);
        model.addAttribute("basketProduct", new ProductEntity());
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String signIn(@ModelAttribute("profileEntity") ProfileEntity profile, ModelMap model) {
        return "redirect:/profile";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addProduct(HttpServletRequest request, ModelMap model) {
        ProductEntity productEntity = dao.findById(Long.parseLong(request.getParameter("id")));
        basket.getBasketProducts().add(productEntity);
        return "redirect:/";
    }

    @RequestMapping(value = "/sort", method = RequestMethod.POST)
    public String sortProducts(ModelMap model) {
        cache.sortByPrice();
        return "redirect:/";
    }

    @RequestMapping(value = "/category", method = RequestMethod.POST)
    public String getByCategory(HttpServletRequest request, ModelMap model) {
        cache.setCategory(request.getParameter("category"));
        return "redirect:/";
    }
}
