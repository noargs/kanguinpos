package com.swingpos.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

public class Product implements Serializable {

    private static int count = 1;

    private int id;
    private int cat_id;
    private String name;
    private String image;
    private BigDecimal price;
    private int is_meal;
    private int is_offer;
    private BigDecimal meal_price;
    private Date created_at;
    private int r;
    private int g;
    private int b;
    private int position;

    public Product(int catId, String name, String image, BigDecimal price, int is_meal,
                   int is_offer, BigDecimal meal_price, Date created_at,
                   int r, int g, int b, int position)
    {
        this.cat_id = catId;
        this.name = name;
        this.image = image;
        this.price = price;
        this.is_meal = is_meal;
        this.is_offer = is_offer;
        this.meal_price = meal_price;
        this.created_at = created_at;
        this.r = r;
        this.g = g;
        this.b = b;
        this.position = position;

        this.id = count;
        count++;
    }

    public Product(int id, int catId, String name, String image, BigDecimal price, int is_meal,
                   int is_offer, BigDecimal meal_price, Date created_at,
                   int r, int g, int b, int position)
    {
        this(catId, name, image, price, is_meal, is_offer, meal_price,
              created_at, r, g, b, position);

        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCatId() {
        return cat_id;
    }

    public void setCatId(int catId) {
        this.cat_id = catId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getIsMeal() {
        return is_meal;
    }

    public void setIsMeal(int is_meal) {
        this.is_meal = is_meal;
    }

    public int getIsOffer() {
        return is_offer;
    }

    public void setIsOffer(int is_offer) {
        this.is_offer = is_offer;
    }

    public BigDecimal getMealPrice() {
        return meal_price;
    }

    public void setMealPrice(BigDecimal meal_price) {
        this.meal_price = meal_price;
    }

    public Date getCreatedAt() {
        return created_at;
    }

    public void setCreatedAt(Date created_at) {
        this.created_at = created_at;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

//    public String toString()
//    {
//        return name;
//    }
}
