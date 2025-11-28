package com.swingpos.ui;

import com.swingpos.utils.IconFactory;
import com.swingpos.utils.RenderFont;
import com.swingpos.config.AppConfig;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuthPanel extends JPanel implements ActionListener  {

    private JButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnClear, btnNext;
    private JSeparator separator;
    private JPasswordField passField;
    private JLabel lblInfo;
    private JPanel row1, row2, row3, row4;
    GridBagConstraints gc = new GridBagConstraints();
    GridBagLayout layout = new GridBagLayout();

    private RenderFont renderFont;
    private Font comfortaaBold;

    private AuthListener authListener;


    public AuthPanel()
    {
        layoutComponent();

//        next.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//                char[] password = passField.getPassword();
//                System.out.println(new String(password));
//
//                    passwordListener.getPassword(new String(password));
//
//            }
//        });

        btn1.addActionListener(this);
        btn2.addActionListener(this);
        btn3.addActionListener(this);
        btn4.addActionListener(this);
        btn5.addActionListener(this);
        btn6.addActionListener(this);
        btn7.addActionListener(this);
        btn8.addActionListener(this);
        btn9.addActionListener(this);
        btnClear.addActionListener(this);
        btn0.addActionListener(this);
        btnNext.addActionListener(this);
    }

    public void layoutComponent()
    {
        renderFont = new RenderFont();
        try {
           comfortaaBold = renderFont.customFont(AppConfig.getComfortaBoldFont(), Font.BOLD, 40f);

        }catch(Exception e)
        {
        }
        setLayout(layout);

        gc.weightx = 0;
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.gridy = 0;
        gc.fill = GridBagConstraints.HORIZONTAL;
        lblInfo = new JLabel("ENTER SECRET KEY");
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

        passField = new JPasswordField(4);
        passField.setEditable(false);
        passField.setBackground(Color.WHITE);
        passField.setPreferredSize(new Dimension(120,50));
        passField.setFont(new Font(null, Font.BOLD, 45));
        gc.weightx = 2.0;
        gc.weighty = 2.0;


        gc.gridx = 0;
        gc.gridy = 2;
        gc.gridwidth = 4;
        gc.insets = new Insets(6, 5, 0, 5);
        gc.fill = GridBagConstraints.HORIZONTAL;
        add(passField, gc);

        row1 = new JPanel(new FlowLayout());
        row2 = new JPanel(new FlowLayout());
        row3 = new JPanel(new FlowLayout());
        row4 = new JPanel(new FlowLayout());


        btn1 = new JButton("1");
        btn1.setFont(comfortaaBold);

        btn1.setPreferredSize(new Dimension(100, 80));
        row1.add(btn1);

        btn2 = new JButton("2");
        btn2.setFont(comfortaaBold);
        btn2.setPreferredSize(new Dimension(100, 80));
        row1.add(btn2);

        btn3 = new JButton("3");
        btn3.setFont(comfortaaBold);
        btn3.setPreferredSize(new Dimension(100, 80));
        row1.add(btn3);

        gc.weightx = 0.5;
        gc.weighty = 0.0;

        gc.gridx = 0;
        gc.gridy = 3;
        gc.gridwidth = 4;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(2, 0, 0, 0);
        add(row1, gc);



        btn4 = new JButton("4");
        btn4.setFont(comfortaaBold);
        btn4.setPreferredSize(new Dimension(100, 80));
        row2.add(btn4);


        btn5 = new JButton("5");
        btn5.setFont(comfortaaBold);
        btn5.setPreferredSize(new Dimension(100, 80));
        row2.add(btn5);

        btn6 = new JButton("6");
        btn6.setFont(comfortaaBold);
        btn6.setPreferredSize(new Dimension(100, 80));
        row2.add(btn6);

        gc.weightx = 0.5;
        gc.weighty = 0.0;

        gc.gridx = 0;
        gc.gridy = 4;
        gc.gridwidth = 4;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(-4, 0, 0, 0);
        add(row2, gc);



        btn7 = new JButton("7");
        btn7.setFont(comfortaaBold);
        btn7.setPreferredSize(new Dimension(100, 80));
        row3.add(btn7);


        btn8 = new JButton("8");
        btn8.setFont(comfortaaBold);
        btn8.setPreferredSize(new Dimension(100, 80));
        row3.add(btn8);


        btn9 = new JButton("9");
        btn9.setFont(comfortaaBold);
        btn9.setPreferredSize(new Dimension(100, 80));
        row3.add(btn9);

        gc.weightx = 0.5;
        gc.weighty = 0.0;

        gc.gridx = 0;
        gc.gridy = 5;
        gc.gridwidth = 4;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(-4, 0, 0, 0);
        add(row3, gc);



        btnClear = new JButton();
        btnClear.setText("CLEAR ALL");
        btnClear.setIcon(IconFactory.getIcon(AppConfig.getIconPath(), AppConfig.getIconClear(), new Dimension(32, 32)));
        btnClear.setIconTextGap(-3);
        btnClear.setMargin(new Insets(0, -2, 0, 2));
        btnClear.setPreferredSize(new Dimension(100, 80));
        row4.add(btnClear);


        btn0 = new JButton("0");
        btn0.setFont(comfortaaBold);
        btn0.setPreferredSize(new Dimension(100, 80));
        row4.add(btn0);


        btnNext  = new JButton(IconFactory.getIcon(AppConfig.getIconPath(), AppConfig.getIconNextSide(), new Dimension(32, 32)));
        btnNext.setPreferredSize(new Dimension(100, 80));
        row4.add(btnNext);

        gc.weightx = 0.5;
        gc.weighty = 0.0;

        gc.gridx = 0;
        gc.gridy = 6;
        gc.gridwidth = 4;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(-4, 0, 0, 0);
        add(row4, gc);


        //Border margin = new EmptyBorder(10, 10, 10, 10);
        //setBorder(new CompoundBorder(BorderFactory.createRaisedBevelBorder(), margin));

    }


    private String append(int key)
    {
        char[] password = passField.getPassword();
        String newPassword = new String(password);
        return newPassword + key;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();


        if(clicked == btn1)
        {
            passField.setText(append(1));
        }
        else if(clicked == btn2)
        {
            passField.setText(append(2));
        }
        else if(clicked == btn3)
        {
            passField.setText(append(3));
        }
        else if(clicked == btn4)
        {
            passField.setText(append(4));
        }
        else if(clicked == btn5)
        {
            passField.setText(append(5));
        }
        else if(clicked == btn6)
        {
            passField.setText(append(6));
        }
        else if(clicked == btn7)
        {
            passField.setText(append(7));
        }
        else if(clicked == btn8)
        {
            passField.setText(append(8));
        }
        else if(clicked == btn9)
        {
            passField.setText(append(9));
        }
        else if(clicked == btn0)
        {
            passField.setText(append(0));
        }
        else if(clicked == btnClear)
        {
            passField.setText("");
        }
        else if(clicked == btnNext)
        {
            String password = new String(passField.getPassword());

            if(authListener != null && !password.equals(""))
            {
                authListener.auth(password);
            }
        }

    }
    public void setAuthListener(AuthListener listener)
    {
        this.authListener = listener;
    }
}
