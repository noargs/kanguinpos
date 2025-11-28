package com.swingpos.model;

import java.math.BigDecimal;
import java.sql.Date;

public class Order extends Item {

    private int orderId;
    private Boolean isVoid;
    private Boolean isProcessed;
    private Boolean isCollection;
    private String OrderComments;
    private BigDecimal totalPrice;
    private Date createdAt;

    public Order (String cookingInstr)
    {
        super(cookingInstr);
    }

    public Order(String itemName, BigDecimal itemPrice, BigDecimal individualPrice, int itemIdentifier, int productId)
    {
        super(itemName, itemPrice, individualPrice, itemIdentifier, productId);
    }

    public Order(String itemName, BigDecimal itemPrice, BigDecimal individualPrice)
    {
        super(itemName, itemPrice, individualPrice);
    }

    public Order(int Quantity, String itemName, BigDecimal itemPrice, BigDecimal individualPrice, int itemIdentifier, int productId)
    {
        super(Quantity, itemName, itemPrice, individualPrice, itemIdentifier, productId);
    }

    @Override
    public int getOrderId() {
        return orderId;
    }

    @Override
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Boolean getVoid() {
        return isVoid;
    }

    public void setVoid(Boolean aVoid) {
        isVoid = aVoid;
    }

    public Boolean getProcessed() {
        return isProcessed;
    }

    public void setProcessed(Boolean processed) {
        isProcessed = processed;
    }

    public Boolean getCollection() {
        return isCollection;
    }

    public void setCollection(Boolean collection) {
        isCollection = collection;
    }

    public String getOrderComments() {
        return OrderComments;
    }

    public void setOrderComments(String orderComments) {
        OrderComments = orderComments;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

//    public String toString()
//    {
//        return this.getItemName();
//    }

}
