package com.makarov.lshop.web.controller;

import com.makarov.lshop.base.component.Basket;
import com.makarov.lshop.base.dao.api.DAO;
import com.makarov.lshop.base.model.ProfileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProfileController {

    @Autowired
    private DAO dao;

    @Autowired
    private Basket basket;

    @RequestMapping(value = "/id{id}", method = RequestMethod.GET)
    public String profile(@PathVariable Long id, ModelMap model) {
        ProfileEntity selectProfile = dao.getProfile(id);
        if (selectProfile == null) {
            return "redirect:/";
        } else {
            model.addAttribute("profile", selectProfile);
            if (basket.isLogin()) {
                model.addAttribute("showButton", id.equals(basket.getProfile().getId()));
            }
            return "profile";
        }
    }
}
