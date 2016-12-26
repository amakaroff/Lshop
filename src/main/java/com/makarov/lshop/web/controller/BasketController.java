package com.makarov.lshop.web.controller;

import com.google.common.collect.Maps;
import com.makarov.lshop.base.component.Basket;
import com.makarov.lshop.base.creator.api.Creator;
import com.makarov.lshop.base.dao.api.DAO;
import com.makarov.lshop.base.model.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class BasketController {

    @Autowired
    private Basket basket;

    @Autowired
    private DAO dao;

    @Autowired
    private Creator<byte[], ProductEntity> creator;

    private boolean isError = false;

    private String errorMessage = "";

    @RequestMapping(value = "/basket", method = RequestMethod.GET)
    public String getBasket(HttpServletRequest request, ModelMap model) {
        Map<ProductEntity, Boolean> isDeleted = Maps.newHashMap();

        List<ProductEntity> list = dao.getProducts(null, false);

        for (ProductEntity item : basket.getBasketProducts()) {
            isDeleted.put(item, list.contains(item));
        }

        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("isDeleted", isDeleted);
        model.addAttribute("products", basket.getBasketProducts());
        model.addAttribute("error", isError);
        isError = false;
        return "basket";
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String removeProduct(HttpServletRequest request, ModelMap model) {
        basket.deleteProduct(Long.parseLong(request.getParameter("id")));
        return "redirect:/basket";
    }

    @RequestMapping(value = "/basket/check.pdf", method = RequestMethod.POST)
    public String buyProducts(HttpServletResponse response, ModelMap model) {
        if (dao.getProducts(null, false).containsAll(basket.getBasketProducts())) {
            if (basket.getBasketProducts().isEmpty()) {
                errorMessage = "The basket is empty. Please buy some products.";
                isError = true;
                return "redirect:/basket";
            }

            //dao.deleteProducts(basket.getBasketProducts());
            byte[] contents = creator.create(basket.getBasketProducts());

            response.setContentType("application/pdf");
            response.setContentLength(contents.length);
            response.setHeader("Content-Disposition", "inline; filename=check.pdf");

            try {
                response.getOutputStream().write(contents);
                response.getOutputStream().flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return "check";
        } else {
            isError = true;
            errorMessage = "Some products have already been bought. Please delete them from the basket.";
            return "redirect:/basket";
        }
    }
}
