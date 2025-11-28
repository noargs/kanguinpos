package com.swingpos.utils;

import com.swingpos.config.AppConfig;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class IconFactory {
    private static HashMap<String, ImageIcon> iconCache = new HashMap<String, ImageIcon>();

    public static ImageIcon getIcon(String iconName)
    {
        ImageIcon icon = iconCache.get(iconName);
        if(icon == null)
        {
            try
            {
                icon = new ImageIcon(IconFactory.class.getResource(AppConfig.getIconPath() + iconName));
                iconCache.put(iconName, icon);
            }catch(Exception e)
            {
                return getDefaultIcon(iconName);
            }
        }
        return icon;
    }

    public static ImageIcon getIcon(String path, String iconName)
    {
        ImageIcon icon = iconCache.get(iconName);
        if(icon == null)
        {
            try
            {
                icon = new ImageIcon(IconFactory.class.getResource(path + iconName));
                iconCache.put(iconName, icon);
            }catch(Exception e)
            {
                return getIcon(iconName);
            }
        }
        return icon;
    }

    public static ImageIcon getDefaultIcon(String iconName)
    {
        ImageIcon icon = iconCache.get(iconName);
        if(icon == null)
        {
            try {
                icon = new ImageIcon(IconFactory.class.getResource(AppConfig.getImagePath() + iconName));
                iconCache.put(iconName, icon);
            }catch(Exception e)
            {
            }
        }
        return icon;
    }

    public static ImageIcon getIcon(String path, String iconName, Dimension size)
    {
        ImageIcon icon = iconCache.get(iconName);
        if(icon == null)
        {
            try{
                icon = new ImageIcon(IconFactory.class.getResource(path + iconName));
                icon = new ImageIcon(icon.getImage().getScaledInstance(size.width, size.height, Image.SCALE_SMOOTH));
            }catch(Exception e)
            {
                System.err.println("Unable to find " + iconName);
                //return getIcon(iconName);
            }
        }
        return icon;
    }
}
