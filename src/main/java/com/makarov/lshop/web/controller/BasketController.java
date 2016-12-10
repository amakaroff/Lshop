package com.makarov.lshop.web.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
public class BasketController {

    @RequestMapping(value = "/basket", method = RequestMethod.POST)
    public String basket(ModelMap model) {
        return "basket";
    }
}
