package com.swingpos.ui;

import com.swingpos.utils.IconFactory;
import com.swingpos.config.AppConfig;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class AuthWindow extends JPanel{

    private JPanel right;
    private JLabel left;
    private JSeparator midSeparator;
    private AuthPanel authPanel;
    private JButton shutDown;

    public AuthWindow(AuthPanel panel)
    {
        layoutComponent(panel);
    }

    public void layoutComponent(AuthPanel panel)
    {
        setLayout(new MigLayout("fill"));
        right = new JPanel();
        right.add(panel);


        midSeparator = new JSeparator(SwingConstants.VERTICAL);

        left = new JLabel(IconFactory.getIcon( AppConfig.getIconAppLogo()));
        left.setVerticalAlignment(JLabel.CENTER);

        shutDown  = new JButton(IconFactory.getIcon(AppConfig.getIconPath(), AppConfig.getIconInfo(), new Dimension(24, 24)));
        shutDown.setPreferredSize(new Dimension(40, 30));

        add(left, "growy, width 29%");
        add(midSeparator, "growy, width 3%");
        add(right, "spany, width 66%");
        add(shutDown, "align right top");
    }





}
