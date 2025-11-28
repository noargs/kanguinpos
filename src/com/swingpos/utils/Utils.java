package com.swingpos.utils;

import com.swingpos.config.AppConfig;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Utils {

    public static <T> List<List<T>> getPages(Collection<T> c, Integer pageSize) {
        if (c == null)
            return Collections.emptyList();
        List<T> list = new ArrayList<T>(c);
        if (pageSize == null || pageSize <= 0 || pageSize > list.size())
            pageSize = list.size();
        int numPages = (int) Math.ceil((double)list.size() / (double)pageSize);
        List<List<T>> pages = new ArrayList<List<T>>(numPages);
        for (int pageNum = 0; pageNum < numPages;)
            pages.add(list.subList(pageNum * pageSize, Math.min(++pageNum * pageSize, list.size())));
        return pages;
    }

    public static boolean tableExist(String table, Connection con) throws SQLException
    {
        boolean tExists = false;

        try(ResultSet rs = con.getMetaData().getTables(null, null, table, null))
        {
            while(rs.next())
            {
                String tName = rs.getString("TABLE_NAME");
                if(tName != null && tName.equals(table))
                {
                    tExists = true;
                    break;
                }
            }
        }
        return tExists;
    }

    public static void arrangeButtonText (String btnName, String price, JButton btn)
    {
        RenderFont rf = new RenderFont();
        Font digital = null;
        try {
            digital = rf.customFont(AppConfig.getDJBDigitalFont(), Font.BOLD, 13f);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        btn.setLayout(new MigLayout("wrap 1, insets 0, align center center"));
        String[] split = btnName.split(" ");
        btn.setHorizontalTextPosition(JLabel.CENTER);
        JLabel lblPrice = new JLabel(price);
        lblPrice.setSize(new Dimension(25, 10));
        lblPrice.setFont(digital);
        lblPrice.setForeground(new Color(41, 128, 185));
        lblPrice.setHorizontalAlignment(JLabel.CENTER);
        if(split.length == 1)
        {
            JLabel lblText1 = new JLabel(split[0]);
            setLblFontPosition(lblText1, new Font("Helvetica", Font.BOLD, 15) );
            btn.add(lblText1);

        }
        else if(split.length == 2)
        {
            JLabel lblText1 = new JLabel(split[0]);
            JLabel lblText2 = new JLabel(split[1]);
            setLblFontPosition(lblText1, new Font("Helvetica", Font.BOLD, 15) );
            setLblFontPosition(lblText2, new Font("Helvetica", Font.BOLD, 15) );
            btn.add(lblText1, "center");
            btn.add(lblText2, "center");
        }
        else if(split.length ==3)
        {
            JLabel lblText1 = new JLabel(split[0]);
            JLabel lblText2 = new JLabel(split[1] + " " + split[2]);
            setLblFontPosition(lblText1, new Font("Helvetica", Font.BOLD, 15) );
            setLblFontPosition(lblText2, new Font("Helvetica", Font.BOLD, 15) );
            btn.add(lblText1, "center");
            btn.add(lblText2, "center");
        }
        else if(split.length == 4 || split.length > 4)
        {
            JLabel lblText1 = new JLabel(split[0]);
            JLabel lblText2 = new JLabel(split[1] + " " + split[2]);
            JLabel lblText3 = new JLabel(split[3]);
            setLblFontPosition(lblText1, new Font("Helvetica", Font.BOLD, 15) );
            setLblFontPosition(lblText2, new Font("Helvetica", Font.BOLD, 15) );
            setLblFontPosition(lblText3, new Font("Helvetica", Font.BOLD, 15) );
            btn.add(lblText1, "center");
            btn.add(lblText2, "center");
            btn.add(lblText3, "center");
        }
        btn.add(lblPrice, "center");
    }

    private static void setLblFontPosition(JLabel lbl, Font font)
    {
        lbl.setFont(font);
        lbl.setHorizontalTextPosition(JLabel.CENTER);
    }

    public static void paginationInfo(JLabel label, int currentPage, int maxPage)
    {
        label.setText("");
        if(maxPage > 1)
        {
            String info = String.valueOf(currentPage) + " / " + String.valueOf(maxPage);
            label.setText(info);
            label.setPreferredSize(new Dimension(20, 10));
            label.setFont(new Font("impact", Font.PLAIN, 16));
            label.setForeground(new Color(127, 140, 141));
        }
    }

    public static void setDialogLocation(JFrame parent, JDialog dialog)
    {
        if(parent != null)
        {
            Dimension parentSize = parent.getSize();
            Point p = parent.getLocation();
            dialog.setLocation(p.x + parentSize.width / 8, p.y + parentSize.height / 8);
        }
    }

}
