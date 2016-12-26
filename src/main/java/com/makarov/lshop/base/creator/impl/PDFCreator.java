package com.makarov.lshop.base.creator.impl;

import com.lowagie.text.*;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfWriter;
import com.makarov.lshop.base.creator.api.Creator;
import com.makarov.lshop.base.model.ProductEntity;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class PDFCreator implements Creator<byte[], ProductEntity> {

    @Override
    public byte[] create(List<ProductEntity> list) {
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, stream);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        document.open();

        Paragraph title = new Paragraph();
        title.setAlignment(Element.ALIGN_CENTER);
        title.setFont(FontFactory.getFont(FontFactory.TIMES_ROMAN, 30, Font.NORMAL, new CMYKColor(255, 255, 255, 255)));
        title.add("Check");

        try {
            document.add(title);
            document.add(new Paragraph("\n\n\n" + getLine("Product name", "Product price") + "\n\n",
                    FontFactory.getFont(FontFactory.TIMES_ROMAN, 16, Font.ITALIC, new CMYKColor(255, 255, 255, 255))));

            int count = 0;
            for (ProductEntity item : list) {
                count += Integer.parseInt(item.getPrice());
                document.add(new Paragraph(getLine(item.getName(), item.getPrice()) + "\n",
                        FontFactory.getFont(FontFactory.TIMES_ROMAN, 16, Font.NORMAL, new CMYKColor(255, 255, 255, 255))));
            }

            document.add(new Paragraph("\n\n" + "Total : " + count + "\n\n",
                    FontFactory.getFont(FontFactory.TIMES_ROMAN, 16, Font.ITALIC, new CMYKColor(255, 255, 255, 255))));

            String dateInString = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
            Paragraph endParagraph = new Paragraph("\n" + "Signature_________________  " + dateInString,
                    FontFactory.getFont(FontFactory.TIMES_ROMAN, 16, Font.ITALIC, new CMYKColor(255, 255, 255, 255)));
            endParagraph.setAlignment(Element.ALIGN_RIGHT);
            document.add(endParagraph);
            Paragraph logo = new Paragraph("LShop",
                    FontFactory.getFont(FontFactory.TIMES_ROMAN, 20, Font.ITALIC, new CMYKColor(255, 255, 255, 255)));
            logo.setAlignment(Element.ALIGN_RIGHT);
            document.add(logo);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        document.close();

        return stream.toByteArray();
    }

    private String getLine(String name, String price) {
        final int MAX_LENGTH_NAME = 40;
        int countSpaceName = MAX_LENGTH_NAME - name.length();
        final int MAX_LENGTH_PRICE = 15;
        int countSpacePrice = MAX_LENGTH_PRICE - price.length();

        StringBuilder spaces = new StringBuilder();
        for (int i = 0; i < countSpaceName; i++) {
            spaces.append(" ");
        }
        String newName = name + spaces.toString();

        spaces = new StringBuilder();
        for (int i = 0; i < countSpacePrice; i++) {
            spaces.append(" ");
        }
        String newPrice = price + spaces.toString();

        return newName + newPrice;
    }
}
