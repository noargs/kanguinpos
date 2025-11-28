package com.swingpos.main;


import com.swingpos.config.AppConfig;
import com.swingpos.controller.Controller;
import com.swingpos.model.CookingInstr;
import com.swingpos.model.Order;
import com.swingpos.swing.GlassPane;
import com.swingpos.ui.*;
import com.swingpos.utils.CategoryPageable;
import com.swingpos.utils.PrintFormat;
import com.swingpos.utils.PrintReceipt;
import com.swingpos.utils.ProductPageable;

import javax.swing.*;
import java.awt.*;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.util.Iterator;

public class PosWindow extends JFrame {

    private GlassPane glassPane;
    private AuthWindow authWindow;
    private AuthPanel authPanel;
    private HomeWindow homeWindow;
    private OrderTableModel orderTableModel;
    private TicketWindow ticketWindow;
    private PayoutWindow payoutWindow;
    private PayoutPad payoutPad;

    private Category category;

    private Product product;
    private Controller contrl;



    private CategoryPageable<com.swingpos.model.Category> categoryPageable;
    private ProductPageable<com.swingpos.model.Product> productPageable;

    final private CardLayout cards = new CardLayout();
    private Container c;

    public PosWindow()
    {
        c = getContentPane();
        c.setLayout(cards);

        glassPane = new GlassPane();
        glassPane.setOpacity(0.2f);
        setGlassPane(glassPane);

        authPanel = new AuthPanel();
        authWindow = new AuthWindow(authPanel);
        homeWindow = new HomeWindow();
        orderTableModel = new OrderTableModel();



        contrl = new Controller();

        try {
            contrl.loadCategory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        category = new Category();
        categoryPageable = new CategoryPageable<com.swingpos.model.Category>(contrl.getCategories());
        category.setSelectedBtnId(contrl.getFirstCategoryId());
        setCategoryPage(getCategoryPage());


        try {
            contrl.loadDefaultProduct();
        } catch (Exception e) {
            e.printStackTrace();
        }
        product = new Product();
        productPageable = new ProductPageable<com.swingpos.model.Product>(contrl.getProducts());
        setProductPage(getProductPage());


        ticketWindow = new TicketWindow(category, product, categoryPageable, productPageable, orderTableModel);

        payoutPad = new PayoutPad();
        payoutWindow = new PayoutWindow(payoutPad, orderTableModel);


        c.add(authWindow, AppConfig.AUTH_VIEW);
        c.add(homeWindow, AppConfig.HOME_VIEW);
        c.add(ticketWindow, AppConfig.TICKET_VIEW);
        c.add(payoutWindow, AppConfig.PAY_VIEW);

        authPanel.setAuthListener(new AuthListener() {
            @Override
            public void auth(String password) {
                cards.show(c, AppConfig.HOME_VIEW);
//                if(password.equals("1234"))
//                {
//                    cards.show(c, "home");
//                }
            }
        });

        category.setCategoryListener(new CategoryListener(){
            public void getCategoryId(int id)
            {
                try {
                    contrl.loadProduct(id);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                productPageable.setPageAbleList(contrl.getProducts());
                setProductPage(getProductPage());
                product.setProduct(productPageable);
                ticketWindow.setLblProductPageInfo(productPageable.getPage(), productPageable.getMaxPages());

                System.out.println(id + " bring id from poswindow");
            }
        });

        product.setProductListener(new ProductListener() {
            @Override
            public void getProductId(int id) {

                // select product that was clicked
                // run checks to see if this selected product is just on its own or meal
                // List<Order> will not be created until checks come true
                contrl.setSelectedProduct(id);


                if(contrl.isMeal())
                {
                    if(contrl.hasMainPrice()) {
                        // TODO show dialogue box with further options
                        new MealDialog(PosWindow.this, contrl, orderTableModel);
                        recalculateTotalPrice();
                    }
                    else{
                        System.out.println("meal but no individual price");
                    }
                }
                else
                {
//                    if(contrl.hasMainPrice())
//                    {

                        // create List<Order> from selected Product
                        contrl.createMainPriceOrder();

                        // grab created order (List<Order>) and insert into table
                        orderTableModel.setData(contrl.getOrders());
                        orderTableModel.fireTableDataChanged();

                        // keeping the last added row visible by scrolling automatically
                        int row = orderTableModel.getRowCount() - 1;
                        JTable table = ticketWindow.getTable();
                        table.scrollRectToVisible(new Rectangle(table.getCellRect(row , 0, true)));


                        recalculateTotalPrice();
//                    }
                }

            }
        });

        ticketWindow.setPosTableListener(new PosTableListener() {
            @Override
            public void getSelectedRow(int row) {
                contrl.removeOrder(row);
                recalculateTotalPrice();
            }
        });

        ticketWindow.setCookingInstrListener(new CookingInstrListener() {
            @Override
            public void getSelectedRow(int row) {
                try { contrl.loadCookingInstr(row); } catch (Exception e) { e.printStackTrace(); }

                if(contrl.getCookingInstrs().iterator().hasNext()) new CookingInstrDialog(PosWindow.this, contrl, orderTableModel, row);

                System.out.println("from poswindow " + row);
            }
        });

        ticketWindow.setMiscItemListener(new MiscItemListener() {
            @Override
            public void openDialog() {
                new MiscItemDialog(PosWindow.this, contrl, orderTableModel);
            }
        });


        ticketWindow.setQuantityListener(new QuantityListener() {
            @Override
            public void increment(int row) {
                contrl.incrementQuantity(row);
                orderTableModel.fireTableDataChanged();
                ticketWindow.getTable().setRowSelectionInterval(row, row);
                recalculateTotalPrice();
            }

            @Override
            public void decrement(int row) {
                contrl.decrementQuantity(row);
                orderTableModel.fireTableDataChanged();
                ticketWindow.getTable().setRowSelectionInterval(row, row);
                recalculateTotalPrice();
            }
        });

        homeWindow.setViewListener(new ViewListener(){
            public void changeView(String view)
            {
                cards.show(c, view);
            }
        });

        ticketWindow.setViewListener(new ViewListener() {
            @Override
            public void changeView(String view) {
                cards.show(c, view);
            }
        });

        payoutWindow.setViewListener(new ViewListener() {
            @Override
            public void changeView(String view) {
                cards.show(c, view);
                payoutPad.resetTxtPaid();
            }
        });

        payoutWindow.setPaymentListener(new PrintListener() {
            @Override
            public void print() {

                PrinterJob printerJob = PrinterJob.getPrinterJob();
                PrintFormat printFormat = new PrintFormat(printerJob);

                printerJob.setPrintable(new PrintReceipt(contrl), printFormat.getPageFormat());

                try{
                    printerJob.print();
                }catch (PrinterException e)
                {
                    e.printStackTrace();
                }


//                System.out.println("--- page to print ---");
//                Iterator iterator = contrl.getOrders().iterator();
//                while (iterator.hasNext())
//                {
//                    Order order = (Order) iterator.next();
//                    System.out.println("qty  product         price");
//                    System.out.println(order.getQty() + " " + order.getItemName() + " " + order.getItemPrice());
//
//                }
            }
        });






        //contrl.disconnectCategory();
    }

    private int getCategoryPage()
    {
        return categoryPageable.getPage();
    }

    private void setCategoryPage(int page)
    {
        categoryPageable.setPage(page);
    }

    private int getProductPage()
    {
        return productPageable.getPage();
    }

    private void setProductPage(int page)
    {
        productPageable.setPage(page);
    }

    private void recalculateTotalPrice()
    {
        ticketWindow.getBtnOrderTotal().setText(contrl.getDefaultCurrency() + contrl.getOrderTotalPrice().toString());
        payoutPad.getTxtDue().setText(contrl.getOrderTotalPrice().toString());
    }

}
