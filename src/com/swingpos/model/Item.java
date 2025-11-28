package com.swingpos.model;

import java.math.BigDecimal;
import java.sql.Date;

public class Item {

    private int id;
    private int productId;
    private int orderId;
    private int qty = 1;
    private int itemIdentifier;
    private String itemName;
    private BigDecimal indPrice;
    private BigDecimal itemPrice;
    private String cookingInstr;
    private String cookingInstr2;


    public Item(String itemName, BigDecimal itemPrice, BigDecimal individualPrice, int itemIdentifier, int productId)
    {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.indPrice = individualPrice;
        this.itemIdentifier = itemIdentifier;
        this.productId = productId;
    }

    public Item(String itemName, BigDecimal itemPrice, BigDecimal individualPrice)
    {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.indPrice = individualPrice;
    }

    public Item(int Quantity, String itemName, BigDecimal itemPrice, BigDecimal individualPrice, int itemIdentifier, int productId)
    {
        this(itemName, itemPrice, individualPrice, itemIdentifier, productId);
        this.qty = Quantity;
    }

    public Item(String cookingInstr)
    {
        this.cookingInstr = cookingInstr;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
//        BigDecimal b1 = new BigDecimal(qty);
//        BigDecimal b2 = getItemPrice().multiply(b1);
//        setItemPrice(b2);
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getIndividualPrice()
    {
        return indPrice;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getItemIdentifier() {
        return itemIdentifier;
    }

    public void setItemIdentifier(int itemIdentifier) {
        this.itemIdentifier = itemIdentifier;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getCookingInstr() {
        return cookingInstr;
    }

    public void setCookingInstr(String cookingInstr) {
        this.cookingInstr = cookingInstr;
    }

    public String getCookingInstr2() {
        return cookingInstr2;
    }

    public void setCookingInstr2(String cookingInstr2) {
        this.cookingInstr2 = cookingInstr2;
    }
}
