package com.makarov.lshop.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Алексей on 10.12.2016.
 */
@Controller
public class BasketController {

    @RequestMapping(value = "/basket", method = RequestMethod.POST)
    public String basket(ModelMap model) {
        return "basket";
    }
}
