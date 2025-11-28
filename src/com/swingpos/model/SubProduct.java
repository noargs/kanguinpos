package com.swingpos.model;

import java.math.BigDecimal;

public class SubProduct {

    private static int count = 1;

    private int id;
    private int productId;
    private String name;
    private String image;
    private BigDecimal price;
    private int visibility;
    private int position;

    public SubProduct(int productId, String name, String image, BigDecimal price, int visibility, int position)
    {
        this.productId = productId;
        this.name = name;
        this.image = image;
        this.price = price;
        this.visibility = visibility;
        this.position = position;

        this.id = count;
        count++;
    }

    public SubProduct(int id, int productId, String name, String image, BigDecimal price, int visibility, int position)
    {
        this(productId, name, image, price, visibility, position);

        this.id = id;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
