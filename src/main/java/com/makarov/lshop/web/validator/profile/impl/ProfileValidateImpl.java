package com.makarov.lshop.web.validator.profile.impl;

import com.makarov.lshop.base.dao.api.DAO;
import com.makarov.lshop.base.model.ProfileEntity;
import com.makarov.lshop.base.service.profile.api.Validator;
import com.makarov.lshop.web.validator.profile.api.ProfileValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileValidateImpl implements ProfileValidator {

    @Autowired
    private Validator validator;

    @Autowired
    private DAO dao;

    @Override
    public String validateProfile(ProfileEntity profile) {

        if (!validator.loginValidate(profile.getLogin())) {
            profile.setLogin("");
            return "invalid login";
        } else if (dao.getProfile(profile.getLogin()) != null) {
            return "login already exists";
        }

        if (!validator.passwordValidate(profile.getPassword())) {
            profile.setPassword("");
            return "invalid password";
        }

        if (!validator.nameValidate(profile.getName())) {
            profile.setName("");
            return "invalid name";
        }

        if (!validator.photoValidate(profile.getUrlPhoto())) {
            profile.setUrlPhoto("");
            return "invalid url photo";
        }

        if (!validator.phoneValidate(profile.getPhone())) {
            profile.setPhone("");
            return "invalid phone number";
        }

        if (!validator.emailValidate(profile.getMail())) {
            profile.setMail("");
            return "invalid email";
        }

        return "";
    }
}
