package com.swingpos.ui;

import com.swingpos.config.AppConfig;
import com.swingpos.utils.ProductPageable;
import com.swingpos.utils.Utils;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.Iterator;

public class Product extends JPanel implements ActionListener {

    private JButton btnProduct;
    private JPanel pnlProduct;
    private ProductListener productListener;

    public Product()
    {
        setLayout(new MigLayout("fill, insets 0"));
        pnlProduct = new JPanel(new MigLayout("fill, wrap 3, insets 0"));
    }

    public void setProduct(ProductPageable pageable)
    {
        if(pageable.getListForPage() != null)
        {
            removeComponents(pnlProduct);

            Iterator itr = pageable.getListForPage().iterator();
            for (int i = 0; itr.hasNext(); i++) {

                btnProduct = new JButton();

                initButtons(itr);

                pnlProduct.add(btnProduct, "center");

            }
            add(pnlProduct );
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();
        Object prop = clicked.getClientProperty("id");

        if(productListener != null) productListener.getProductId((int) prop);
    }

    private void removeComponents(JPanel panel)
    {
        Component[] components = panel.getComponents();
        for(Component component: components)
        {
            pnlProduct.remove(component);
        }
        pnlProduct.revalidate();
        pnlProduct.repaint();
    }

    private void initButtons(Iterator iterator)
    {
        btnProduct.setOpaque(true);
        btnProduct.setPreferredSize(new Dimension(105, 75));
        btnProduct.setMinimumSize(new Dimension(105, 75));
        btnProduct.setFont(new Font("Helvetica", Font.BOLD, 14));
        btnProduct.setHorizontalTextPosition(JLabel.CENTER);
        btnProduct.setVerticalTextPosition(JLabel.BOTTOM);

        btnProduct.addActionListener(this);

        com.swingpos.model.Product pd = (com.swingpos.model.Product)iterator.next();

        StringBuilder sb = new StringBuilder();
        BigDecimal price = pd.getPrice();
        BigDecimal mealPrice = pd.getMealPrice();
        if(price.compareTo(BigDecimal.ZERO) != 0)
        {
            sb.append(AppConfig.getDefaultCurrency());
            sb.append(" ");
            sb.append(price.toString());

            if(mealPrice.compareTo(BigDecimal.ZERO) != 0)
            {
                sb.append("/");
                sb.append(mealPrice);
            }
        }
        else
        {
            sb.append("Free");
        }
        Utils.arrangeButtonText(pd.getName(), sb.toString(), btnProduct);

//        btnProduct.setIcon(IconFactory.getIcon(AppConfig.getImagePath(), pd.getImage(), new Dimension(30, 30)));

//        btnProduct.setBackground(new java.awt.Color(pd.getR(), pd.getG(), pd.getB()));

        btnProduct.putClientProperty("id", pd.getId());


        //btnProduct.setMargin(new Insets(0,-10,-4,-10));
        btnProduct.setToolTipText("Select product");
    }

    public void setProductListener(ProductListener listener)
    {
        this.productListener = listener;
    }
}
