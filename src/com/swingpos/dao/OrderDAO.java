package com.swingpos.dao;

import com.swingpos.model.Order;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class OrderDAO {

    private List<Order> orders;

    public OrderDAO()
    {
        orders = new LinkedList<Order>();
    }

    public List<Order> getOrders()
    {
        return orders;
    }

    public void addOrder(Order e)
    {
        this.orders.add(e);
    }

    public void setOrder(List<Order> order)
    {
        this.orders = order;
    }

    public void removeOrder(int index)
    {
        orders.remove(index);
    }
}
