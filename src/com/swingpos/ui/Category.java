package com.swingpos.ui;

import com.swingpos.config.AppConfig;
import com.swingpos.utils.IconFactory;
import com.swingpos.utils.CategoryPageable;
import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

public class Category extends JPanel implements ActionListener {

    private JButton btnCategory;
    private JPanel pnlCategory;
    private CategoryListener categoryListener;
    private static int selectedCategoryId = 0;
    private ArrayList<JButton> categoryBtns;

    public Category()
    {
        setLayout(new MigLayout("fill, insets 0"));
        pnlCategory = new JPanel(new MigLayout("fill, wrap 1, insets 0"));
        categoryBtns = new ArrayList<JButton>();
    }

    public void setCategory(CategoryPageable pageable)
    {
        if(pageable.getListForPage() != null)
        {
            removeComponents(pnlCategory);
            categoryBtns.clear();

            Iterator itr = pageable.getListForPage().iterator();
            for (int i = 0; itr.hasNext(); i++) {

                btnCategory = new JButton();

                initButtons(itr);


                pnlCategory.add(btnCategory, "center");

            }
            add(pnlCategory, "grow y");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();
        int prop = (int)clicked.getClientProperty("id");

        for(JButton jButton: categoryBtns)
        {
            int id = (int) jButton.getClientProperty("id");

            if(prop != id)
            {
                jButton.setEnabled(true);
            }
            else
            {
                jButton.setEnabled(false);
                jButton.setFocusPainted(false);
            }

        }

        if(categoryListener != null) {
            categoryListener.getCategoryId((int) prop);
            setSelectedBtnId(prop);
        }
    }

    private void removeComponents(JPanel panel)
    {
        Component[] components = panel.getComponents();
        for(Component component: components)
        {
            pnlCategory.remove(component);
        }
        pnlCategory.revalidate();
        pnlCategory.repaint();
    }

    private void initButtons(Iterator iterator)
    {
        btnCategory.setOpaque(true);
        btnCategory.setPreferredSize(new Dimension(100, 75));
        btnCategory.setMaximumSize(new Dimension(100, 75));
        btnCategory.setMinimumSize(new Dimension(100, 75));

        btnCategory.addActionListener(this);

        com.swingpos.model.Category ct = (com.swingpos.model.Category)iterator.next();

        btnCategory.setText(ct.getName());
        btnCategory.setIcon(IconFactory.getIcon(AppConfig.getImagePath(), ct.getImage(), new Dimension(30, 30)));
        btnCategory.setHorizontalTextPosition(JLabel.CENTER);
        btnCategory.setVerticalTextPosition(JLabel.BOTTOM);
        btnCategory.setBackground(new java.awt.Color(ct.getR(), ct.getG(), ct.getB()));

        btnCategory.putClientProperty("id", ct.getId());


        btnCategory.setFocusPainted(false);

        if(getSelectedCategoryId() == ct.getId())
        {
            btnCategory.setEnabled(false);
        }


        btnCategory.setFont(new Font("Helvetica", Font.BOLD, 14));
        btnCategory.setMargin(new Insets(0,-10,-4,-10));
        btnCategory.setToolTipText("Select category");

        //buttonGroup.add(btnCategory);
        categoryBtns.add(btnCategory);
    }

    public void setCategoryListener(CategoryListener listener)
    {
        this.categoryListener = listener;
    }

    public void setSelectedBtnId(int id)
    {
        selectedCategoryId = id;

    }

    public int getSelectedCategoryId()
    {
        return selectedCategoryId;
    }



}
