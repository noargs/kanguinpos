package com.swingpos.main;

import com.jgoodies.looks.plastic.PlasticXPLookAndFeel;
import com.jgoodies.looks.plastic.theme.DesertBluer;
import com.swingpos.config.AppConfig;
import com.swingpos.utils.IconFactory;
import com.swingpos.config.AppProperties;


import javax.swing.*;

public class Application{

    private PosWindow posWindow;

    private static ImageIcon applicationIcon;
    private final static String VERSION = AppProperties.getVersion();

    public Application()
    {
        setApplicationLook();

        applicationIcon = IconFactory.getIcon(AppConfig.getIconPath(), AppConfig.getIconAppIcon62());

        posWindow = new PosWindow();
        posWindow.setIconImage(applicationIcon.getImage());

        System.out.println("--- application.java --- [running...]");

        posWindow.setPreferredSize(AppConfig.getPreferredWidthAndHeight());
        posWindow.setMinimumSize(AppConfig.getMinimumWidthAndHeight());
        posWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        posWindow.setVisible(true);

    }


    private void setApplicationLook()
    {
        try {

            PlasticXPLookAndFeel.setPlasticTheme(/* new ExperienceBlue()*/ new DesertBluer());
            UIManager.setLookAndFeel(new PlasticXPLookAndFeel());

        } catch (Exception ignored) {
        }
    }


    public static ImageIcon getApplicationIcon()
    {
        return applicationIcon;
    }

    public static String getTitle()
    {
        return AppProperties.getAppName() + " - Version " + VERSION;
    }
}
