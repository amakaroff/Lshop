package com.makarov.lshop.web.controller;

import com.makarov.lshop.base.dao.api.DAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Controller
public class HomeShopController {

    @Autowired
    private DAO dao;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String hello(ModelMap model) {
        return "index.jsp";
    }
}
