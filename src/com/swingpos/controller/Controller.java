package com.swingpos.controller;

import com.swingpos.config.AppConfig;
import com.swingpos.dao.*;
import com.swingpos.model.CookingInstr;
import com.swingpos.model.Order;
import com.swingpos.model.Product;
import com.swingpos.model.SubProduct;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Controller {
    private CategoryDAO categoryDAO = new CategoryDAO();
    private ProductDAO productDAO = new ProductDAO();
    private SubProductDAO subProductDAO = new SubProductDAO();
    private OrderDAO orderDAO = new OrderDAO();
    private CookingInstrDAO cookingInstrDAO = new CookingInstrDAO();

    //private List<Order> orders = new LinkedList<Order>();



    public List<com.swingpos.model.Category> getCategories()
    {
        return categoryDAO.getCategories();
    }

    public int getFirstCategoryId()
    {
        return categoryDAO.getFirstCategoryId();
    }

    public void removeCategory(int index) {
        categoryDAO.removeCategory(index);
    }

    public void saveCategory() throws SQLException {
        categoryDAO.save();
    }

    public void loadCategory() throws Exception {
        categoryDAO.load();
    }

    public void disconnectCategory() {
        categoryDAO.disconnect();
    }

    public List<com.swingpos.model.Product> getProducts()
    {
        return productDAO.getProducts();
    }

    public void loadDefaultProduct()throws Exception{
        productDAO.load(getFirstCategoryId());

    }

    public void loadProduct(int CatId) throws Exception {
        productDAO.load(CatId);
    }

    public ArrayList<Product> getSelectedProduct()
    {
        return productDAO.getSelectedProduct();
    }

    public int getSelectedProductId()
    {
        return productDAO.getSelectedProductId();
    }

    public int getOrderCatId(int productId)
    {
        return productDAO.getOrderCatId(productId);
    }

    // select a product by id
    // run different checks
    public void setSelectedProduct(int productId)
    {
        productDAO.setSelectedProduct(productId);

    }

    // product selected above by id
    // grab that selected product check to see if its meal
    // if meal let button click for further options
    // (1) if on its own then insert into List<Order>
    // OR
    // (2) if meal button clicked insert into List<Order> accordingly
    public boolean isMeal()
    {
        return productDAO.isMeal();
    }

    // product selected above by id
    // grab that selected product check to see if it has main price
    // then insert into List<Order>
    public boolean hasMainPrice()
    {
        return productDAO.hasMainPrice();
    }

    public List<Order> getOrders()
    {
        return orderDAO.getOrders();
    }

    // run once when byitself button clicked
    public void createMainPriceOrder() {
        Iterator iterator = productDAO.getSelectedProduct().iterator();
        while(iterator.hasNext())
        {
            Product product = (Product) iterator.next();
            int id = product.getId();
            String name = product.getName();
            BigDecimal price = product.getPrice().setScale(2, BigDecimal.ROUND_HALF_UP);
            BigDecimal individualPrice = product.getPrice().setScale(2, BigDecimal.ROUND_HALF_UP);
            Order order = new Order(name, price, individualPrice, AppConfig.IND_PRICE_IDENTIFIER, id);
            orderDAO.addOrder(order);
        }
    }

    public void createMiscOrder(String itemName, BigDecimal itemPrice)
    {
        Order order = new Order(itemName, itemPrice, itemPrice);
        orderDAO.addOrder(order);
    }

    public void loadSubProduct() throws Exception
    {
        if(getSelectedProductId() != 0)
        {
            subProductDAO.load(getSelectedProductId());
        }
    }

    public List<SubProduct> getSubProducts()
    {
        return subProductDAO.getSubProducts();
    }

    public void createMealPriceOrder(String subProduct, BigDecimal subProductPrice)
    {
        StringBuilder sb = new StringBuilder();

        Iterator iterator = productDAO.getSelectedProduct().iterator();
        Product product = (Product) iterator.next();
        int id = product.getId();
        String name = product.getName();

        sb.append(name);
        sb.append(" [");
        sb.append(AppConfig.MEAL);
        sb.append("/");
        sb.append(subProduct);
        sb.append("]");
        BigDecimal price = product.getMealPrice().setScale(2, BigDecimal.ROUND_HALF_UP);

        try{
            if(subProductPrice.compareTo(BigDecimal.ZERO) != 0) price = price.add(subProductPrice);
        }catch(NullPointerException e)
        {
        }

        //BigDecimal totalPrice = price.add(subProductPrice);
        BigDecimal individualMealPrice = product.getMealPrice().setScale(2, BigDecimal.ROUND_HALF_UP);
        try{
            if(subProductPrice.compareTo(BigDecimal.ZERO) != 0) individualMealPrice = individualMealPrice.add(subProductPrice);
        }catch(NullPointerException e1)
        {
        }
        //BigDecimal totalIndvMealPrice = individualMealPrice.add(subProductPrice);
        Order order = new Order(sb.toString(), price, individualMealPrice, AppConfig.MEAL_PRICE_IDENTIFIER, id);
        orderDAO.addOrder(order);

    }

    public void incrementQuantity(int row)
    {
        Order order = (Order)getOrders().get(row);
        int quantity = order.getQty();
        quantity += 1;

        order.setQty(quantity);

        String itemName = order.getItemName();

        BigDecimal invidualPrice = order.getIndividualPrice();
        BigDecimal price = invidualPrice.multiply(new BigDecimal(quantity));

        order.setQty(quantity);
        order.setItemPrice(price);

        getOrders().set(row, order);
    }

    public void decrementQuantity(int row)
    {
        Order order = (Order)getOrders().get(row);
        int quantity = order.getQty();
        if(quantity != 1)
        {
            quantity -= 1;
            order.setQty(quantity);
            String itemName = order.getItemName();
            BigDecimal invidualPrice = order.getIndividualPrice();
            BigDecimal price = invidualPrice.multiply(new BigDecimal(quantity));
            order.setQty(quantity);
            order.setItemPrice(price);
            getOrders().set(row, order);
        }


    }

    public BigDecimal getOrderTotalPrice(){
        Iterator iterator = getOrders().iterator();
        BigDecimal totalPrice = new BigDecimal(0);
            while(iterator.hasNext())
            {
                Order order = (Order)iterator.next();
                BigDecimal price = order.getItemPrice();
                totalPrice = totalPrice.add(price);
            }
         return totalPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
    }



    public int getOrderListSize()
    {
        return orderDAO.getOrders().size();
    }

    public void removeOrder(int index)
    {
        orderDAO.removeOrder(index);
    }

    public String getDefaultCurrency()
    {
        AppConfig.put("currency", AppConfig.getDefaultCurrency());
        return AppConfig.getString("currency");
    }

    public void loadCookingInstr(int row) throws Exception {
        Order order = (Order)getOrders().get(row);
        int productId = order.getProductId();
        int catId = getOrderCatId(productId);

        cookingInstrDAO.load(catId);

    }

    public List<CookingInstr> getCookingInstrs()
    {
        return cookingInstrDAO.getCookingInstrs();
    }

}
