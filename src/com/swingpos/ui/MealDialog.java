package com.swingpos.ui;

import com.swingpos.config.AppConfig;
import com.swingpos.controller.Controller;
import com.swingpos.utils.IconFactory;
import com.swingpos.utils.RenderFont;
import com.swingpos.utils.Utils;
import javafx.scene.control.ToggleButton;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;

public class MealDialog extends JDialog{

    private JPanel pnlHeader;
    private JPanel pnlBody;
    private JPanel pnlBodyContent;
    private JPanel pnlFooter;

    private JToggleButton btnByItself;
    private JToggleButton btnMeal;
    private JButton btnCancel;
    private JButton btnOk;
    private JToggleButton btnSubProduct;
    private ButtonGroup buttonGroup;
    private ButtonGroup buttonGroup2;

    private String choice = "";
    private BigDecimal subProductPrice;

    private Double count = 0.0;
    private final int BUTTONS_IN_ROW = 4;
    private final int DIALOG_DEFAULT_MIN_HEIGHT = AppConfig.getDialogMinimumSize().height;
    private final int BUTTONS_HEIGHT = 75;
    private final int BUTTONS_WIDTH = 110;
    private final int INSETS_VERTICAL = 7;


    private Font comforta;
    private Font strados;
    private Font djb;

    private OrderTableModel orderTableModel;
    private Controller controller;


    private int width = AppConfig.getDialogMinimumSize().width;
    private int height = AppConfig.getDialogMinimumSize().height;


    public MealDialog(JFrame parent, Controller contrl, OrderTableModel orderTableModel)
    {
        super(parent, "Select an option",true);

        this.orderTableModel = orderTableModel;
        this.controller = contrl;
        setUndecorated(true);


        initFont();
        initLayout();
        loadSubProducts();
        Utils.setDialogLocation(parent, MealDialog.this);
        //setDialogLocation(parent);
        setDialogContent();
        setDialogSize();


    }

    private void initLayout()
    {
        setLayout(new MigLayout("fill, insets 0"));
        pnlBody = new JPanel(new MigLayout("fill, insets 0 0 0 2"));

    }

    private void initFont()
    {
        RenderFont rf = new RenderFont();
        try {
            comforta = rf.customFont(AppConfig.getComfortaBoldFont(), Font.BOLD, 20f);
            strados = rf.customFont(AppConfig.getFontStrados(), Font.BOLD, 20f);
            djb = rf.customFont(AppConfig.getDJBDigitalFont(), Font.BOLD, 13f);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

//    private void setDialogLocation(JFrame parent)
//    {
//        if(parent != null)
//        {
//            Dimension parentSize = parent.getSize();
//            Point p = parent.getLocation();
//            setLocation(p.x + parentSize.width / 8, p.y + parentSize.height / 8);
//        }
//    }


    private void setDialogContent()
    {
        buttonGroup = new ButtonGroup();
        buttonGroup2 = new ButtonGroup();

        TitledBorder tb = new TitledBorder("Select an option");
        tb.setTitleFont(comforta);

        Border eb = BorderFactory.createEmptyBorder(10, 3, 0, 3);
        CompoundBorder cb = BorderFactory.createCompoundBorder(eb, tb);
        pnlBody.setBorder(cb);

        getContentPane().add(pnlBody, "growx, wrap");

        pnlHeader = new JPanel(new FlowLayout());

        Iterator iterator = controller.getSelectedProduct().iterator();

        com.swingpos.model.Product product = (com.swingpos.model.Product)iterator.next();

        BigDecimal price = product.getPrice();
        BigDecimal mealPrice = product.getMealPrice();

        // if byitself doesnt have price it means product only offer btnMeal NOT by itself
        //


        btnByItself = new JToggleButton();
        arrangeButtonText("By itself", price, btnByItself);
        buttonGroup.add(btnByItself);
        pnlHeader.add(btnByItself);


        btnByItself.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnlBodyContent.setVisible(false);
            }
        });

        btnMeal = new JToggleButton();
        arrangeButtonText("Meal", mealPrice, btnMeal);
        buttonGroup.add(btnMeal);
        pnlHeader.add(btnMeal);

        btnMeal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnlBodyContent.setVisible(true);

            }
        });

        pnlBody.add(pnlHeader, "align left top, spanx");

        pnlBodyContent = new JPanel(new MigLayout("fill, insets 2 5 2 2, wrap 4"));


        if(controller.getSelectedProductId() != 0)
        {

            Iterator iterator1 = controller.getSubProducts().iterator();

            if(!iterator1.hasNext()) choice = "-";

            for(int i=0; iterator1.hasNext(); i++)
            {
                btnSubProduct = new JToggleButton();
                btnSubProduct.setLayout(new MigLayout("insets 0, align center center, wrap"));
                btnSubProduct.setOpaque(true);

                com.swingpos.model.SubProduct subProduct = (com.swingpos.model.SubProduct) iterator1.next();

                JLabel lbl1 = new JLabel(IconFactory.getIcon(AppConfig.getImagePath(),subProduct.getImage(), new Dimension(75, 51) ));
                String cost;
                if(subProduct.getPrice().compareTo(BigDecimal.ZERO) != 0)
                {
                    cost = subProduct.getPrice().toString();
                }else{
                    cost = "FREE";
                }

                btnSubProduct.add(lbl1);

                JLabel lbl2 = new JLabel(cost);
                lbl2.setFont(djb);
                lbl2.setForeground(new Color(41, 128, 185));
                btnSubProduct.add(lbl2, "center");

                btnSubProduct.setPreferredSize(new Dimension(BUTTONS_WIDTH, BUTTONS_HEIGHT));
                btnSubProduct.setMinimumSize(new Dimension(BUTTONS_WIDTH, BUTTONS_HEIGHT));
                btnSubProduct.setHorizontalTextPosition(JLabel.CENTER);
                btnSubProduct.setVerticalTextPosition(JLabel.BOTTOM);
                //btnSubProduct.setFont(djb);


                buttonGroup2.add(btnSubProduct);
                pnlBodyContent.add(btnSubProduct);

                btnSubProduct.putClientProperty("name", subProduct.getName());
                btnSubProduct.putClientProperty("cost", subProduct.getPrice());

                btnSubProduct.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JToggleButton clicked = (JToggleButton)e.getSource();
                        choice = (String) clicked.getClientProperty("name");
                        subProductPrice = (BigDecimal) clicked.getClientProperty("cost");
                    }
                });

                count++;
            }

        }




        pnlBodyContent.setVisible(false);


        pnlFooter = new JPanel(new FlowLayout());
        btnCancel = new JButton("Cancel");
        btnCancel.setPreferredSize(new Dimension(90, 45));
        btnCancel.setFont(new Font("Helvetica", Font.PLAIN, 18));
        pnlFooter.add(btnCancel);
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });


        btnOk = new JButton("Ok");
        btnOk.setPreferredSize(new Dimension(90, 45));
        btnOk.setFont(new Font("Helvetica", Font.BOLD, 18));
        btnOk.setBackground(Color.GRAY);
        btnOk.setForeground(Color.WHITE);
        pnlFooter.add(btnOk);
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(btnByItself.isSelected())
                {
                    choice = "";
                    //create List<Order> from selected Product
                    controller.createMainPriceOrder();

                    // grab created order (List<Order>) and insert into table
                   // orderTableModel.setData(controller.getOrders());
                    //orderTableModel.fireTableDataChanged();

                    refresh();
                }
                else if(btnMeal.isSelected())
                {

                    if (!choice.equals("")) {
                        controller.createMealPriceOrder(choice, subProductPrice);

                        refresh();
                    }

                    //System.out.println(choice);
                    //System.out.println("meal is selected");
                }

            }
        });

        pnlBody.add(pnlBodyContent, "wrap");
        pnlBody.add(pnlFooter, "align right bottom");

        pack();
        setVisible(true);

    }

    private void arrangeButtonText(String btnName, BigDecimal btnPrice, JToggleButton btn )
    {
        btn.setLayout(new MigLayout("insets 0, wrap, align center center"));

        JLabel lbl1Btn1 = new JLabel(btnName);
        lbl1Btn1.setFont(strados);

        JLabel lbl2Btn1 = new JLabel();
        lbl2Btn1.setFont(djb);
        lbl2Btn1.setForeground(new Color(41, 128, 185));

        if(btnPrice.compareTo(BigDecimal.ZERO) != 0 ) lbl2Btn1.setText(AppConfig.getDefaultCurrency() + " " + btnPrice.toString());

        btn.add(lbl1Btn1, "center");
        btn.add(lbl2Btn1, "center");

        btn.setPreferredSize(new Dimension(BUTTONS_WIDTH, BUTTONS_HEIGHT));

        btn.setFont(strados);
    }

    private void loadSubProducts()
    {
        try {
            controller.loadSubProduct();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    private void refresh()
    {
        orderTableModel.setData(controller.getOrders());
        orderTableModel.fireTableDataChanged();
        setVisible(false);
        dispose();
    }

    private void setDialogSize()
    {
        int totalRows = (int) Math.ceil(count/BUTTONS_IN_ROW);
        int rowHeight = totalRows * (BUTTONS_HEIGHT + INSETS_VERTICAL);
        height = rowHeight + DIALOG_DEFAULT_MIN_HEIGHT;

        setMinimumSize(new Dimension(width, height));
        setPreferredSize(new Dimension(width, height));
    }




}
