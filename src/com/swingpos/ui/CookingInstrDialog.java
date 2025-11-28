package com.swingpos.ui;

import com.swingpos.config.AppConfig;
import com.swingpos.controller.Controller;
import com.swingpos.utils.RenderFont;
import com.swingpos.utils.Utils;
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
import java.util.Iterator;

public class CookingInstrDialog extends JDialog {

    private Controller controller;
    private JPanel pnlBody;
    private JPanel pnlBodyContent;
    private JPanel pnlFooter;

    private JToggleButton btnCookingInstrs;
    private JButton btnCancel;
    private JButton btnOk;

    private int rowSelected;


    private Font comforta;
    private Font djb;

    private int width = 400;
    private int height = 600;

    private int BUTTONS_WIDTH = 110;
    private int BUTTONS_HEIGHT = 75;

    public CookingInstrDialog(JFrame parent, Controller controller, OrderTableModel orderTableModel, int row)
    {
        super(parent, "Product Modifier", true);

        this.controller = controller;
        this.rowSelected = row;

        setUndecorated(true);

        initFont();
        initLayout();
        titledBorder();
        Utils.setDialogLocation(parent, CookingInstrDialog.this);







        pnlBodyContent = new JPanel(new MigLayout("insets 0, fill, wrap 5"));


        if(controller.getSelectedProductId() != 0){
            Iterator itr = controller.getCookingInstrs().iterator();

            for(int i=0; itr.hasNext(); i++)
            {
                btnCookingInstrs = new JToggleButton();
                btnCookingInstrs.setLayout(new MigLayout("insets 0, align center center, wrap"));
                btnCookingInstrs.setOpaque(true);

                com.swingpos.model.CookingInstr cookingInstr = (com.swingpos.model.CookingInstr) itr.next();

                JLabel lbl1 = new JLabel(cookingInstr.getName());
                lbl1.setFont(new Font("Helvetica", Font.PLAIN, 16));
                btnCookingInstrs.add(lbl1);


                String cost;
                if(cookingInstr.getPrice().compareTo(BigDecimal.ZERO) != 0)
                {
                    cost = cookingInstr.getPrice().toString();
                }
                else
                {
                    cost = "FREE";
                }
                JLabel lbl2 = new JLabel(cost);
                lbl2.setFont(djb);
                lbl2.setForeground(new Color(41, 128, 185));
                btnCookingInstrs.add(lbl2, "center");

                btnCookingInstrs.setPreferredSize(new Dimension(BUTTONS_WIDTH, BUTTONS_HEIGHT));
                btnCookingInstrs.setMinimumSize(new Dimension(BUTTONS_WIDTH, BUTTONS_HEIGHT));
                btnCookingInstrs.setHorizontalTextPosition(JLabel.CENTER);
                btnCookingInstrs.setVerticalTextPosition(JLabel.BOTTOM);

                btnCookingInstrs.putClientProperty("name", cookingInstr.getName());
                btnCookingInstrs.putClientProperty("cost", cookingInstr.getPrice());

                pnlBodyContent.add(btnCookingInstrs);

                btnCookingInstrs.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JToggleButton clicked = (JToggleButton) e.getSource();
                        String instrName = (String) clicked.getClientProperty("name");
                        System.out.println(instrName);
                    }
                });

            }
        }




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
                //setVisible(false);
                //dispose();
            }
        });

        pnlBody.add(pnlBodyContent, "wrap");
        pnlBody.add(pnlFooter, "align right bottom");

        pack();
        setVisible(true);

    }

    private void initLayout()
    {
        setLayout(new MigLayout("fill, insets 0"));
        pnlBody = new JPanel(new MigLayout("fill, insets 0 0 0 2"));

        getContentPane().add(pnlBody, "growx, wrap");

    }

    private void titledBorder()
    {
        TitledBorder tb = new TitledBorder("Product Modifier");
        tb.setTitleFont(comforta);

        Border eb = BorderFactory.createEmptyBorder(10, 3, 0, 3);
        CompoundBorder cb = BorderFactory.createCompoundBorder(eb, tb);
        pnlBody.setBorder(cb);
    }

    private void initFont()
    {
        RenderFont rf = new RenderFont();
        try {
            comforta = rf.customFont(AppConfig.getComfortaBoldFont(), Font.BOLD, 20f);
            djb = rf.customFont(AppConfig.getDJBDigitalFont(), Font.BOLD, 13f);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

//    private void loadCookingInstr()
//    {
//        try {
//            controller.loadCookingInstr(rowSelected);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}
