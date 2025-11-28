package com.swingpos.ui;

import com.swingpos.utils.IconFactory;
import com.swingpos.config.AppConfig;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class HomeWindow extends JPanel implements ActionListener {
    // Logo 183, 96
    private JPanel pnlAppHeader;
    private JLabel lblAppLogo;
    private JPanel pnlAppBody;
    private JButton btnPos;
    private JButton btnOrder;
    private JButton btnAdmin;
    private JButton btnKitchen;
    private JButton btnClockIn;
    private JButton btnLogOut;
    private JButton btnShutDown;
    private JLabel lblRestoName;
    private JLabel gapSmall;
    private JLabel lblGapMedium;
    private JPanel lblAppFooter;


    private ViewListener viewListener;

    public HomeWindow()
    {
        layoutComponent();
    }

    private void layoutComponent()
    {
        setLayout(new MigLayout("fill, insets 0"));

        pnlAppHeader = new JPanel();
        lblAppLogo = new JLabel();
        // 183 96
        lblAppLogo.setIcon(IconFactory.getIcon(AppConfig.getIconPath(), AppConfig.getIconAppLogo(), new Dimension(183, 96)));
        lblAppLogo.setBackground(Color.WHITE);
        lblAppLogo.setOpaque(true);
        pnlAppHeader.setBorder(BorderFactory.createRaisedBevelBorder());
        pnlAppHeader.setBackground(Color.WHITE);
        pnlAppHeader.add(lblAppLogo);

        //appBody = new JPanel(new MigLayout("fill, insets 0, debug"));
        pnlAppBody = new JPanel(new MigLayout("fill, insets 0"));
        pnlAppBody.setBorder(new EmptyBorder(0, 100, 14, 100));
        lblRestoName = new JLabel("KEBAB HOUSE");
        lblRestoName.setFont(new Font("sen-serif", Font.BOLD, 25));
        lblRestoName.setPreferredSize(new Dimension(0, 90));
        lblRestoName.setBorder(new EmptyBorder(0, 0, 35, 0));
        pnlAppBody.add(lblRestoName, "cell 0 0 6 1, center");

//		gapSmall = new JLabel();
//		gapSmall.setBorder(BorderFactory.createEmptyBorder(7, 0, 7, 0));
//		appBody.add(gapSmall, "center, wrap");

        btnPos = new JButton();
        btnPos.setText("POS");
        btnPos.setIcon(IconFactory.getIcon(AppConfig.getIconPath(), AppConfig.getIconPOS(), new Dimension(30, 30)));
        btnPos.setIconTextGap(3);
        btnPos.setPreferredSize(new Dimension(700, 90));
        btnPos.setFont(new Font(null, Font.PLAIN, 12));
        pnlAppBody.add(btnPos, "cell 0 1 4 1, growx");

        btnPos.addActionListener(this);

        lblGapMedium = new JLabel();
        lblGapMedium.setBorder(BorderFactory.createEmptyBorder(35, 0, 35, 0));
        pnlAppBody.add(lblGapMedium, "cell 0 2 4 1");

        btnOrder = new JButton();
        btnOrder.setText("ORDERS");
        btnOrder.setIcon(IconFactory.getIcon(AppConfig.getIconPath(), AppConfig.getIconOrder(), new Dimension(30, 30)));
        btnOrder.setIconTextGap(10);
        btnOrder.setPreferredSize(new Dimension(0, 90));
        btnOrder.setPreferredSize(new Dimension(230, 90));
        btnOrder.setFont(new Font(null, Font.PLAIN, 12));
        pnlAppBody.add(btnOrder, "cell 0 3 1 1, growx");

        btnAdmin = new JButton();
        btnAdmin.setText("ADMIN");
        btnAdmin.setIcon(IconFactory.getIcon(AppConfig.getIconPath(), AppConfig.getIconAdmin(), new Dimension(35, 35)));
        btnAdmin.setIconTextGap(4);
        btnAdmin.setPreferredSize(new Dimension(230, 90));
        btnAdmin.setFont(new Font(null, Font.PLAIN, 12));
        pnlAppBody.add(btnAdmin, "cell 1 3 1 1, growx");

        btnKitchen = new JButton();
        btnKitchen.setText("KITCHEN");
        btnKitchen.setIcon(IconFactory.getIcon(AppConfig.getIconPath(), AppConfig.getIconKitchen(), new Dimension(30, 30)));
        btnKitchen.setIconTextGap(2);
        btnKitchen.setPreferredSize(new Dimension(230, 90));
        btnKitchen.setFont(new Font(null, Font.PLAIN, 12));
        pnlAppBody.add(btnKitchen, "cell 2 3 1 1, growx, wrap");

        btnClockIn = new JButton();
        btnClockIn.setText("CLOCK IN");
        btnClockIn.setIcon(IconFactory.getIcon(AppConfig.getIconPath(), AppConfig.getIconClockIn(), new Dimension(30, 30)));
        btnClockIn.setIconTextGap(10);
        btnClockIn.setPreferredSize(new Dimension(0, 90));
        btnClockIn.setFont(new Font(null, Font.PLAIN, 12));
        pnlAppBody.add(btnClockIn, "cell 0 4 1 1, growx");

        btnLogOut = new JButton();
        btnLogOut.setText("LOG OUT");
        btnLogOut.setIcon(IconFactory.getIcon(AppConfig.getIconPath(), AppConfig.getIconLogOut(), new Dimension(30, 30)));
        btnLogOut.setIconTextGap(10);
        //buttLogOut.setMargin(new Insets(0, 0, 0, 2));
        btnLogOut.setPreferredSize(new Dimension(0, 90));
        btnLogOut.setFont(new Font(null, Font.PLAIN, 12));
        pnlAppBody.add(btnLogOut, "cell 1 4 1 2, growx");

        btnShutDown = new JButton();
        btnShutDown.setText("SHUT DOWN");
        btnShutDown.setForeground(Color.WHITE);
        btnShutDown.setBackground(Color.RED);
        btnShutDown.setIcon(IconFactory.getIcon(AppConfig.getIconPath(), AppConfig.getIconShutDown(), new Dimension(32, 30)));
        btnShutDown.setIconTextGap(6);
        //buttShutDown.setMargin(new Insets(0, -2, 0, 2));
        btnShutDown.setPreferredSize(new Dimension(0, 90));
        btnShutDown.setFont(new Font(null, Font.BOLD, 12));
        pnlAppBody.add(btnShutDown, "cell 2 4 1 1, growx");

        lblAppFooter = new JPanel(new MigLayout("fill, insets 0"));

        MatteBorder matteBorder = new MatteBorder(1, 0, 0, 0, Color.GRAY);
        Border border = BorderFactory.createCompoundBorder(matteBorder, BorderFactory.createEmptyBorder(7, 10, 5, 10));
        lblAppFooter.setBorder(border);
        JLabel user = new JLabel("User: Kebab House");
        user.setFont(new Font("courier", Font.PLAIN, 14));
        lblAppFooter.add(user, "align left");

        Clock time = new Clock("time");
        lblAppFooter.add(time, "align right");

//		JLabel pipe = new JLabel("|");
//		pipe.setFont(new Font("courier", Font.PLAIN, 14));
//		pipe.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
//	    appFooter.add(pipe);
//
//		Clock day = new Clock("day");
//		day.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
//		appFooter.add(day);
//
//		JLabel pipe2 = new JLabel("|");
//		pipe2.setFont(new Font("courier", Font.PLAIN, 14));
//		pipe2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
//	    appFooter.add(pipe2);
//
//		Clock date = new Clock("date");
//		date.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
//		appFooter.add(date);

        add(pnlAppHeader, "top, growx, width 100%, wrap");
        add(pnlAppBody, "width 100%, wrap");
        add(lblAppFooter, "bottom, growx,");
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();

        if(clicked == btnPos) {
            if (viewListener != null) {
                viewListener.changeView(AppConfig.TICKET_VIEW);

            }
        }
    }

    public void setViewListener(ViewListener listener)
    {
        this.viewListener = listener;
    }
}
