package com.makarov.lshop.web.controller;

import com.lowagie.text.*;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfWriter;
import com.makarov.lshop.base.component.Basket;
import com.makarov.lshop.base.model.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Controller
public class BasketController {

    @Autowired
    private Basket basket;

    @RequestMapping(value = "/basket", method = RequestMethod.POST)
    public String basket(ModelMap model) {
        model.addAttribute("products", basket.getBasketProducts());
        return "basket";
    }

    @RequestMapping(value = "/basket", method = RequestMethod.GET)
    public String getBasket(ModelMap model) {
        model.addAttribute("products", basket.getBasketProducts());
        return "basket";
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String removeProduct(HttpServletRequest request, ModelMap model) {
        basket.deleteProduct(Long.parseLong(request.getParameter("id")));
        return "redirect:/basket";
    }

    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public String buyProducts(HttpServletResponse response, ModelMap model) {
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, stream);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        document.open();

        Anchor anchorTarget = new Anchor("Check");
        anchorTarget.setName("BackToTop");
        Paragraph paragraph1 = new Paragraph();

        paragraph1.setSpacingBefore(50);
        paragraph1.add(anchorTarget);

        try {
            document.add(paragraph1);
            for (ProductEntity item : basket.getBasketProducts())
                document.add(new Paragraph(item.getName() + " " + item.getPrice() + "\n",
                        FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD, new CMYKColor(255, 255, 255, 255))));
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        document.close();

        byte[] contents = stream.toByteArray();

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
    }

    @RequestMapping(value = "/back", method = RequestMethod.POST)
    public String backToTitle(ModelMap model) {
        return "redirect:/";
    }
}
