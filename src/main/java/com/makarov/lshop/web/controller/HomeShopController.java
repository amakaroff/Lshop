package com.makarov.lshop.web.controller;

import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
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
    private DAO dao;

    @Autowired
    private Basket basket;

    private boolean isError = false;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(HttpServletRequest request, ModelMap model) {
        String category = request.getParameter("category");
        boolean sortIsNeeded = "do".equals(request.getParameter("sort"));

        List<ProductEntity> categoryProducts = dao.getProducts(category, sortIsNeeded);
        model.addAttribute("products", FluentIterable
                .from(categoryProducts)
                .filter(new Predicate<ProductEntity>() {
                    @Override
                    public boolean apply(ProductEntity productEntity) {
                        return !basket.getBasketProducts().contains(productEntity);
                    }
                })
                .toList());

        model.addAttribute("selectCategory", category);
        model.addAttribute("selectProfile", basket.getProfile());
        model.addAttribute("isLogin", basket.isLogin());
        model.addAttribute("error", isError);
        isError = false;
        if (!basket.isLogin()) {
            model.addAttribute("profileEntity", new ProfileEntity());
        }
        return "index";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout(ModelMap model) {
        basket.getBasketProducts().clear();
        basket.setIsLogin(false);
        return "redirect:/";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String signIn(@ModelAttribute("profileEntity") ProfileEntity profile, ModelMap model) {
        ProfileEntity profileEntity = dao.getProfile(profile.getLogin());

        if (profileEntity != null && profileEntity.getPassword().equals(profile.getPassword())) {
            basket.setIsLogin(true);
            basket.setProfile(profileEntity);
            basket.getBasketProducts().clear();
            return "redirect:/id" + profileEntity.getId();
        } else {
            isError = true;
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addProduct(HttpServletRequest request, ModelMap model) {
        ProductEntity productEntity = dao.findById(Long.parseLong(request.getParameter("id")));

        if (dao.getProducts(null, false).contains(productEntity)) {
            basket.getBasketProducts().add(productEntity);
        }

        String category = request.getParameter("selectCategory");

        if ("".equals(category) || category == null) {
            category = "All";
        }

        return "redirect:/?category=" + category;
    }
}
