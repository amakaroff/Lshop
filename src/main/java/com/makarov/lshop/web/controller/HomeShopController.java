package com.makarov.lshop.web.controller;

import com.makarov.lshop.base.dao.api.DAO;
import com.makarov.lshop.base.model.ProfileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeShopController {

    @Autowired
    private DAO dao;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap model) {
        model.addAttribute("profileEntity", new ProfileEntity());
        model.addAttribute("products", dao.getProducts());
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String signIn(@ModelAttribute("profileEntity") ProfileEntity profile, ModelMap model) {
        return "redirect:/profile";
    }
}
