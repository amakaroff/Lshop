package com.makarov.lshop.web.controller;

import com.makarov.lshop.base.component.Basket;
import com.makarov.lshop.base.dao.api.DAO;
import com.makarov.lshop.base.model.ProfileEntity;
import com.makarov.lshop.web.validator.profile.api.ProfileValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SignUpController {

    @Autowired
    private DAO dao;

    @Autowired
    private Basket basket;

    @Autowired
    private ProfileValidator validator;

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String register(ModelMap model) {
        model.addAttribute("profile", new ProfileEntity());
        return "signup";
    }

    @RequestMapping(value = "/complete", method = RequestMethod.POST)
    public String complete(@ModelAttribute("profile") ProfileEntity profile, ModelMap model) {
        trimAllFields(profile);
        String error = validator.validateProfile(profile);

        if (!"".equals(error)) {
            model.addAttribute("errorMessage", error);
            return "signup";
        }

        dao.saveProfile(profile);
        basket.setIsLogin(true);
        basket.setProfile(profile);
        return "redirect:/id" + profile.getId();
    }

    private void trimAllFields(ProfileEntity profileEntity) {
        profileEntity.setLogin(profileEntity.getLogin().trim());
        profileEntity.setPassword(profileEntity.getPassword().trim());
        profileEntity.setName(profileEntity.getName().trim());
        profileEntity.setPhone(profileEntity.getPhone().trim());
        profileEntity.setMail(profileEntity.getMail().trim());
    }
}
