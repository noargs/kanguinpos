package com.swingpos.utils;

import com.sun.istack.internal.NotNull;
import com.swingpos.config.AppConfig;
import com.swingpos.controller.Controller;
import com.swingpos.model.Order;

import javax.swing.text.html.HTMLDocument;
import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.util.Iterator;

public class PrintReceipt implements Printable {

    private Controller controller;

    public PrintReceipt(Controller controller)
    {
        this.controller = controller;
    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException
    {
        int result = NO_SUCH_PAGE;

        if(pageIndex == 0)
        {
            Graphics2D g2d = (Graphics2D) graphics;

            double width = pageFormat.getImageableWidth();

            g2d.translate((int)pageFormat.getImageableX(), (int)pageFormat.getImageableY());

            FontMetrics metrics = g2d.getFontMetrics(new Font("Arial", Font.BOLD, 7));

            int idLength = metrics.stringWidth("000");
            int amtLength = metrics.stringWidth("000000");
            int qtyLength = metrics.stringWidth("00000");
            int priceLength = metrics.stringWidth("000000");
            int prodLength = (int)width - idLength - amtLength - qtyLength - priceLength - 17;

            int productPosition = 0;
            int discountPosition = prodLength+5;
            int pricePosition = discountPosition +idLength+10;
            int qtyPosition = pricePosition + priceLength + 4;
            int amtPosition = qtyPosition + qtyLength;

            try{
                // Draw Header
                int y = 20;
                int yShift = 15;
                int headerRectHeight=15;
                int headerRectHeighta=40;

                g2d.setFont(new Font("Monospaced", Font.PLAIN, AppConfig.RECEIPT_FONT_SIZE));

                g2d.drawString("                                         ", 10, y); y += yShift;

                Iterator iterator = controller.getOrders().iterator();
                while (iterator.hasNext())
                {
                    Order order = (Order) iterator.next();

                    g2d.drawString(" " + order.getQty() + " " + formatItemName(order.getItemName())  + order.getItemPrice() + " ", 4, y); y += yShift;

                   // System.out.println(" " + order.getQty() + " " + formatItemName(order.getItemName()) + " "  + order.getItemPrice() + " ");
                }

            }catch(Exception e)
            {
                e.printStackTrace();
            }
            result = PAGE_EXISTS;
        }
        return result;
    }

    private String formatItemName(@NotNull String str)
    {
        int stringLength = str.length();
        int stringPadding = 0;

        if(stringLength < AppConfig.DEFAULT_ITEM_NAME_LENGTH)
        {
            stringPadding = AppConfig.DEFAULT_ITEM_NAME_LENGTH - stringLength;
        }
        else if(stringLength > AppConfig.DEFAULT_ITEM_NAME_LENGTH)
        {
            int extraChar = stringLength - AppConfig.DEFAULT_ITEM_NAME_LENGTH;

            str = str.substring(0, str.length() - extraChar);
        }
        StringBuilder formatedString = new StringBuilder(str);

        for(int i = 0; i <= stringPadding; i++)
        {
            formatedString.append(" ");
        }
        return formatedString.toString();
    }

}
