package com.swingpos.ui;

import com.swingpos.config.AppConfig;
import com.swingpos.controller.Controller;
import com.swingpos.utils.IconFactory;
import com.swingpos.utils.RenderFont;
import com.swingpos.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

public class MiscItemDialog extends JDialog implements ActionListener {

    private Controller controller;
    private OrderTableModel orderTableModel;
    private JButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0;
    private JButton btnQ, btnW, btnE, btnR, btnT, btnY, btnU, btnI, btnO, btnP;
    private JButton btnA, btnS, btnD, btnF, btnG, btnH, btnJ, btnK, btnL, btnDash;
    private JButton btnClearAlph, btnZ, btnX, btnC, btnV, btnB, btnN, btnM, btnDot, btnClearDigit;
    private JButton                   btnSpace;
    private JButton btnCancel, btnOk;
    private JSeparator separator;
    private JTextField txtName;
    private JTextField txtPrice;

    private JLabel lblItem;
    private JLabel lblPrice;

    private JLabel lblInfo;
    private JPanel row1, row2, row3, row4, row5, row6, row7, row8;
    GridBagConstraints gc = new GridBagConstraints();
    GridBagLayout layout = new GridBagLayout();

    private int btnWidth = 60;
    private int btnHeight = 40;
    private RenderFont renderFont;

    private Font comfortaaBold;

    public MiscItemDialog(JFrame parent, Controller controller, OrderTableModel orderTableModel)
    {
        super(parent, "Enter miscellaneous item", true);

        this.controller = controller;
        this.orderTableModel = orderTableModel;

        layoutComponent();

        Utils.setDialogLocation(parent, MiscItemDialog.this);

        btn1.addActionListener(this);
        btn2.addActionListener(this);
        btn3.addActionListener(this);
        btn4.addActionListener(this);
        btn5.addActionListener(this);
        btn6.addActionListener(this);
        btn7.addActionListener(this);
        btn8.addActionListener(this);
        btn9.addActionListener(this);
        btn0.addActionListener(this);

        btnQ.addActionListener(this);
        btnW.addActionListener(this);
        btnE.addActionListener(this);
        btnR.addActionListener(this);
        btnT.addActionListener(this);
        btnY.addActionListener(this);
        btnU.addActionListener(this);
        btnI.addActionListener(this);
        btnO.addActionListener(this);
        btnP.addActionListener(this);

        btnA.addActionListener(this);
        btnS.addActionListener(this);
        btnD.addActionListener(this);
        btnF.addActionListener(this);
        btnG.addActionListener(this);
        btnH.addActionListener(this);
        btnJ.addActionListener(this);
        btnK.addActionListener(this);
        btnL.addActionListener(this);
        btnDash.addActionListener(this);

        btnClearAlph.addActionListener(this);
        btnZ.addActionListener(this);
        btnX.addActionListener(this);
        btnC.addActionListener(this);
        btnV.addActionListener(this);
        btnB.addActionListener(this);
        btnN.addActionListener(this);
        btnM.addActionListener(this);
        btnDot.addActionListener(this);
        btnClearDigit.addActionListener(this);

        btnSpace.addActionListener(this);

        btnCancel.addActionListener(this);
        btnOk.addActionListener(this);

        pack();
        setVisible(true);
    }

    public void layoutComponent()
    {
        renderFont = new RenderFont();
        try {
            comfortaaBold = renderFont.customFont(AppConfig.getComfortaRegularFont(), Font.PLAIN, 20f);

        }catch(Exception e)
        {
        }
        setLayout(layout);

        gc.weightx = 0;
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.gridy = 0;
        gc.fill = GridBagConstraints.HORIZONTAL;
        lblInfo = new JLabel("Enter miscellaneous item");
        lblInfo.setFont(new Font(null, Font.BOLD, 22));
        gc.gridwidth = 4;
        gc.insets = new Insets(3, 5, 10, 0);
        add(lblInfo, gc);




        separator = new JSeparator(SwingConstants.HORIZONTAL);
        gc.weightx = 2.0;
        gc.weighty = 2.0;

        gc.gridx = 0;
        gc.gridy = 1;

        gc.gridwidth = 4;
        gc.insets = new Insets(3, 5, 3, 5);
        gc.fill = GridBagConstraints.HORIZONTAL;
        add(separator, gc);

        row1 = new JPanel(new FlowLayout(FlowLayout.LEFT));

        lblItem = new JLabel("Name");
        lblItem.setPreferredSize(new Dimension(100, 40));
        lblItem.setFont(new Font("Helvetica", Font.PLAIN, 17));
        row1.add(lblItem);

        txtName = new JTextField(7);
        txtName.setEditable(false);
        txtName.setBackground(Color.WHITE);
        txtName.setPreferredSize(new Dimension(100,30));
        txtName.setMinimumSize(new Dimension(100,30));
        txtName.setFont(new Font(null, Font.BOLD, 25));
        row1.add(txtName);

        gc.weightx = 1.0;
        gc.weighty = 1.0;

        gc.gridx = 0;
        gc.gridy = 2;
        gc.gridwidth = 4;
        gc.insets = new Insets(0, 5, 0, 5);
        gc.fill = GridBagConstraints.HORIZONTAL;
        add(row1, gc);


        row2 = new JPanel(new FlowLayout(FlowLayout.LEFT));

        lblPrice = new JLabel("Price (" + AppConfig.getDefaultCurrency() + ")");
        lblPrice.setPreferredSize(new Dimension(100, 40));
        lblPrice.setFont(new Font("Helvetica", Font.PLAIN, 17));
        row2.add(lblPrice);


        txtPrice = new JTextField(5);
        txtPrice.setEditable(false);
        txtPrice.setBackground(Color.WHITE);
        txtPrice.setPreferredSize(new Dimension(100, 30));
        txtPrice.setMinimumSize(new Dimension(100, 30));
        txtPrice.setFont(new Font(null, Font.BOLD, 25));
        row2.add(txtPrice);

        gc.weightx = 1.0;
        gc.weighty = 1.0;

        gc.gridx = 0;
        gc.gridy = 3;
        gc.gridwidth = 4;
        gc.insets = new Insets(0, 5, 0, 5);
        gc.fill = GridBagConstraints.HORIZONTAL;
        add(row2, gc);


        row3 = new JPanel(new FlowLayout());
        row4 = new JPanel(new FlowLayout());
        row5 = new JPanel(new FlowLayout());
        row6 = new JPanel(new FlowLayout());
        row7 = new JPanel(new FlowLayout());
        row8 = new JPanel(new FlowLayout(FlowLayout.RIGHT));


        btn1 = new JButton("1");
        btn1.setFont(comfortaaBold);

        btn1.setPreferredSize(new Dimension(btnWidth, btnHeight));
        row3.add(btn1);

        btn2 = new JButton("2");
        btn2.setFont(comfortaaBold);
        btn2.setPreferredSize(new Dimension(btnWidth, btnHeight));
        row3.add(btn2);

        btn3 = new JButton("3");
        btn3.setFont(comfortaaBold);
        btn3.setPreferredSize(new Dimension(btnWidth, btnHeight));
        row3.add(btn3);

        btn4 = new JButton("4");
        btn4.setFont(comfortaaBold);
        btn4.setPreferredSize(new Dimension(btnWidth, btnHeight));
        row3.add(btn4);

        btn5 = new JButton("5");
        btn5.setFont(comfortaaBold);
        btn5.setPreferredSize(new Dimension(btnWidth, btnHeight));
        row3.add(btn5);

        btn6 = new JButton("6");
        btn6.setFont(comfortaaBold);
        btn6.setPreferredSize(new Dimension(btnWidth, btnHeight));
        row3.add(btn6);

        btn7 = new JButton("7");
        btn7.setFont(comfortaaBold);
        btn7.setPreferredSize(new Dimension(btnWidth, btnHeight));
        row3.add(btn7);

        btn8 = new JButton("8");
        btn8.setFont(comfortaaBold);
        btn8.setPreferredSize(new Dimension(btnWidth, btnHeight));
        row3.add(btn8);

        btn9 = new JButton("9");
        btn9.setFont(comfortaaBold);
        btn9.setPreferredSize(new Dimension(btnWidth, btnHeight));
        row3.add(btn9);

        btn0 = new JButton("0");
        btn0.setFont(comfortaaBold);
        btn0.setPreferredSize(new Dimension(btnWidth, btnHeight));
        row3.add(btn0);

        gc.weightx = 0.5;
        gc.weighty = 0.0;

        gc.gridx = 0;
        gc.gridy = 4;
        gc.gridwidth = 4;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(2, 0, 0, 0);
        add(row3, gc);



        btnQ = new JButton("Q");
        btnQ.setFont(comfortaaBold);
        btnQ.setPreferredSize(new Dimension(btnWidth, btnHeight));
        row4.add(btnQ);

        btnW = new JButton("W");
        btnW.setFont(comfortaaBold);
        btnW.setPreferredSize(new Dimension(btnWidth, btnHeight));
        row4.add(btnW);

        btnE = new JButton("E");
        btnE.setFont(comfortaaBold);
        btnE.setPreferredSize(new Dimension(btnWidth, btnHeight));
        row4.add(btnE);

        btnR = new JButton("R");
        btnR.setFont(comfortaaBold);
        btnR.setPreferredSize(new Dimension(btnWidth, btnHeight));
        row4.add(btnR);

        btnT = new JButton("T");
        btnT.setFont(comfortaaBold);
        btnT.setPreferredSize(new Dimension(btnWidth, btnHeight));
        row4.add(btnT);

        btnY = new JButton("Y");
        btnY.setFont(comfortaaBold);
        btnY.setPreferredSize(new Dimension(btnWidth, btnHeight));
        row4.add(btnY);

        btnU = new JButton("U");
        btnU.setFont(comfortaaBold);
        btnU.setPreferredSize(new Dimension(btnWidth, btnHeight));
        row4.add(btnU);

        btnI = new JButton("I");
        btnI.setFont(comfortaaBold);
        btnI.setPreferredSize(new Dimension(btnWidth, btnHeight));
        row4.add(btnI);

        btnO = new JButton("O");
        btnO.setFont(comfortaaBold);
        btnO.setPreferredSize(new Dimension(btnWidth, btnHeight));
        row4.add(btnO);

        btnP = new JButton("P");
        btnP.setFont(comfortaaBold);
        btnP.setPreferredSize(new Dimension(btnWidth, btnHeight));
        row4.add(btnP);

        gc.weightx = 0.5;
        gc.weighty = 0.0;

        gc.gridx = 0;
        gc.gridy = 5;
        gc.gridwidth = 4;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(-4, 0, 0, 0);
        add(row4, gc);

        btnA = new JButton("A");
        btnA.setFont(comfortaaBold);
        btnA.setPreferredSize(new Dimension(btnWidth, btnHeight));
        row5.add(btnA);

        btnS = new JButton("S");
        btnS.setFont(comfortaaBold);
        btnS.setPreferredSize(new Dimension(btnWidth, btnHeight));
        row5.add(btnS);

        btnD = new JButton("D");
        btnD.setFont(comfortaaBold);
        btnD.setPreferredSize(new Dimension(btnWidth, btnHeight));
        row5.add(btnD);

        btnF = new JButton("F");
        btnF.setFont(comfortaaBold);
        btnF.setPreferredSize(new Dimension(btnWidth, btnHeight));
        row5.add(btnF);

        btnG = new JButton("G");
        btnG.setFont(comfortaaBold);
        btnG.setPreferredSize(new Dimension(btnWidth, btnHeight));
        row5.add(btnG);

        btnH = new JButton("H");
        btnH.setFont(comfortaaBold);
        btnH.setPreferredSize(new Dimension(btnWidth, btnHeight));
        row5.add(btnH);

        btnJ = new JButton("J");
        btnJ.setFont(comfortaaBold);
        btnJ.setPreferredSize(new Dimension(btnWidth, btnHeight));
        row5.add(btnJ);

        btnK = new JButton("K");
        btnK.setFont(comfortaaBold);
        btnK.setPreferredSize(new Dimension(btnWidth, btnHeight));
        row5.add(btnK);

        btnL = new JButton("L");
        btnL.setFont(comfortaaBold);
        btnL.setPreferredSize(new Dimension(btnWidth, btnHeight));
        row5.add(btnL);

        btnDash = new JButton("-");
        btnDash.setFont(comfortaaBold);
        btnDash.setPreferredSize(new Dimension(btnWidth, btnHeight));
        row5.add(btnDash);

        gc.weightx = 0.5;
        gc.weighty = 0.0;

        gc.gridx = 0;
        gc.gridy = 6;
        gc.gridwidth = 4;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(-4, 0, 0, 0);
        add(row5, gc);


//        JLabel lbl1 = new JLabel();
//        lbl1.setMinimumSize(new Dimension(btnWidth, btnHeight));
//        row6.add(lbl1);

        btnClearAlph = new JButton("abc");
        btnClearAlph.setIcon(IconFactory.getIcon(AppConfig.getIconPath(), AppConfig.getIconClear(), new Dimension(32, 32)));
        btnClearAlph.setIconTextGap(-3);
        btnClearAlph.setMargin(new Insets(0, -2, 0, 2));
        btnClearAlph.setPreferredSize(new Dimension(btnWidth, btnHeight));
        row6.add(btnClearAlph);

        btnZ = new JButton("Z");
        btnZ.setFont(comfortaaBold);
        btnZ.setPreferredSize(new Dimension(btnWidth, btnHeight));
        row6.add(btnZ);

        btnX = new JButton("X");
        btnX.setFont(comfortaaBold);
        btnX.setPreferredSize(new Dimension(btnWidth, btnHeight));
        row6.add(btnX);

        btnC = new JButton("C");
        btnC.setFont(comfortaaBold);
        btnC.setPreferredSize(new Dimension(btnWidth, btnHeight));
        row6.add(btnC);

        btnV = new JButton("V");
        btnV.setFont(comfortaaBold);
        btnV.setPreferredSize(new Dimension(btnWidth, btnHeight));
        row6.add(btnV);

        btnB = new JButton("B");
        btnB.setFont(comfortaaBold);
        btnB.setPreferredSize(new Dimension(btnWidth, btnHeight));
        row6.add(btnB);

        btnN = new JButton("N");
        btnN.setFont(comfortaaBold);
        btnN.setPreferredSize(new Dimension(btnWidth, btnHeight));
        row6.add(btnN);

        btnM = new JButton("M");
        btnM.setFont(comfortaaBold);
        btnM.setPreferredSize(new Dimension(btnWidth, btnHeight));
        row6.add(btnM);

        btnDot = new JButton(".");
        btnDot.setFont(comfortaaBold);
        btnDot.setPreferredSize(new Dimension(btnWidth, btnHeight));
        row6.add(btnDot);

        btnClearDigit = new JButton("123");
        btnClearDigit.setIcon(IconFactory.getIcon(AppConfig.getIconPath(), AppConfig.getIconClear(), new Dimension(32, 32)));
        btnClearDigit.setIconTextGap(-3);
        btnClearDigit.setMargin(new Insets(0, -2, 0, 2));
        btnClearDigit.setPreferredSize(new Dimension(btnWidth, btnHeight));
        row6.add(btnClearDigit);

        gc.weightx = 0.5;
        gc.weighty = 0.0;

        gc.gridx = 0;
        gc.gridy = 7;
        gc.gridwidth = 4;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(-4, 0, 0, 0);
        add(row6, gc);


//        JLabel lbl2 = new JLabel();
//        lbl2.setPreferredSize(new Dimension(btnWidth, btnHeight));
//        row7.add(lbl2);

        btnSpace = new JButton("");
        btnSpace.setFont(comfortaaBold);
        btnSpace.setPreferredSize(new Dimension(300, btnHeight));
        row7.add(btnSpace);

        gc.weightx = 0.5;
        gc.weighty = 0.0;

        gc.gridx = 0;
        gc.gridy = 8;
        gc.gridwidth = 4;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(-4, 0, 0, 0);
        add(row7, gc);

        btnCancel = new JButton("Cancel");
        btnCancel.setFont(new Font("Helvetica", Font.PLAIN, 16));
        btnCancel.setPreferredSize(new Dimension(110, btnHeight));
        row8.add(btnCancel);

        btnOk = new JButton("Ok");
        btnOk.setFont(new Font("Helvetica", Font.BOLD, 16));
        btnOk.setBackground(Color.BLACK);
        btnOk.setForeground(Color.WHITE);
        btnOk.setPreferredSize(new Dimension(110, btnHeight));
        row8.add(btnOk);

        btnOk.addActionListener(e -> {
            System.out.println("ok clicked");
        });

        gc.weightx = 0.5;
        gc.weighty = 0.0;

        gc.gridx = 0;
        gc.gridy = 9;
        gc.gridwidth = 4;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(-4, 0, 0, 0);
        add(row8, gc);


        //Border margin = new EmptyBorder(10, 10, 10, 10);
        //setBorder(new CompoundBorder(BorderFactory.createRaisedBevelBorder(), margin));

    }


    private String appendAlpha(String key)
    {
//        char[] password = passField.getPassword();
//        String newPassword = new String(password);
        String txt = txtName.getText();
        return txt + key;
    }

    private String appendDigit(String key)
    {
        String txt = txtPrice.getText();
        return txt + key;
    }

    private static String removeLastChar(String str) {
        try{
            return str.substring(0, str.length() - 1);
        }catch(StringIndexOutOfBoundsException e)
        {
        }catch (NullPointerException e2)
        {
        }
        return "";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();


        if(clicked == btn1)
        {
            txtPrice.setText(appendDigit("1"));
        }
        else if(clicked == btn2)
        {
            txtPrice.setText(appendDigit("2"));
        }
        else if(clicked == btn3)
        {
            txtPrice.setText(appendDigit("3"));
        }
        else if(clicked == btn4)
        {
            txtPrice.setText(appendDigit("4"));
        }
        else if(clicked == btn5)
        {
            txtPrice.setText(appendDigit("5"));
        }
        else if(clicked == btn6)
        {
            txtPrice.setText(appendDigit("6"));
        }
        else if(clicked == btn7)
        {
            txtPrice.setText(appendDigit("7"));
        }
        else if(clicked == btn8)
        {
            txtPrice.setText(appendDigit("8"));
        }
        else if(clicked == btn9)
        {
            txtPrice.setText(appendDigit("9"));
        }
        else if(clicked == btn0)
        {
            txtPrice.setText(appendDigit("0"));
        }
        else if(clicked == btnDot)
        {
            txtPrice.setText(appendDigit("."));
        }
        else if(clicked == btnQ)
        {
            txtName.setText(appendAlpha("Q"));
        }
        else if(clicked == btnW)
        {
            txtName.setText(appendAlpha("W"));
        }
        else if(clicked == btnE)
        {
            txtName.setText(appendAlpha("E"));
        }
        else if(clicked == btnR)
        {
            txtName.setText(appendAlpha("R"));
        }
        else if(clicked == btnT)
        {
            txtName.setText(appendAlpha("T"));
        }
        else if(clicked == btnY)
        {
            txtName.setText(appendAlpha("Y"));
        }
        else if(clicked == btnU)
        {
            txtName.setText(appendAlpha("U"));
        }
        else if(clicked == btnI)
        {
            txtName.setText(appendAlpha("I"));
        }
        else if(clicked == btnO)
        {
            txtName.setText(appendAlpha("O"));
        }
        else if(clicked == btnP)
        {
            txtName.setText(appendAlpha("P"));
        }
        else if(clicked == btnA)
        {
            txtName.setText(appendAlpha("A"));
        }
        else if(clicked == btnS)
        {
            txtName.setText(appendAlpha("S"));
        }
        else if(clicked == btnD)
        {
            txtName.setText(appendAlpha("D"));
        }
        else if(clicked == btnF)
        {
            txtName.setText(appendAlpha("F"));
        }
        else if(clicked == btnG)
        {
            txtName.setText(appendAlpha("G"));
        }
        else if(clicked == btnH)
        {
            txtName.setText(appendAlpha("H"));
        }
        else if(clicked == btnJ)
        {
            txtName.setText(appendAlpha("J"));
        }
        else if(clicked == btnK)
        {
            txtName.setText(appendAlpha("K"));
        }
        else if(clicked == btnL)
        {
            txtName.setText(appendAlpha("L"));
        }
        else if(clicked == btnDash)
        {
            txtName.setText(appendAlpha("-"));
        }
        else if(clicked == btnZ)
        {
            txtName.setText(appendAlpha("Z"));
        }
        else if(clicked == btnX)
        {
            txtName.setText(appendAlpha("X"));
        }
        else if(clicked == btnC)
        {
            txtName.setText(appendAlpha("C"));
        }
        else if(clicked == btnV)
        {
            txtName.setText(appendAlpha("V"));
        }
        else if(clicked == btnB)
        {
            txtName.setText(appendAlpha("B"));
        }
        else if(clicked == btnN)
        {
            txtName.setText(appendAlpha("N"));
        }
        else if(clicked == btnM)
        {
            txtName.setText(appendAlpha("M"));
        }
        else if(clicked == btnSpace)
        {
            txtName.setText(appendAlpha(" "));
        }
        else if(clicked == btnClearAlph)
        {
            String edit = removeLastChar(txtName.getText());
            txtName.setText(edit);
        }
        else if(clicked == btnClearDigit)
        {
            String edit = removeLastChar(txtPrice.getText());
            txtPrice.setText(edit);
        }
        else if(clicked == btnCancel)
        {
            setVisible(false);
            dispose();
        }
        else if(clicked == btnOk)
        {
            String itemName = txtName.getText();
            String itemPrice = txtPrice.getText();
            if(!itemName.equals("") && !itemPrice.equals(""))
            {
                BigDecimal price = new BigDecimal(itemPrice).setScale(2, BigDecimal.ROUND_UP);
                controller.createMiscOrder(itemName, price);
                orderTableModel.setData(controller.getOrders());
                orderTableModel.fireTableDataChanged();

                setVisible(false);
                dispose();
            }
        }




    }
}
