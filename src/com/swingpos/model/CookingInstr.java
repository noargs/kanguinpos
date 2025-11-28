package com.swingpos.model;

import java.math.BigDecimal;

public class CookingInstr {

    private static int count = 0;

    int id;
    int cat_id;
    String name;
    BigDecimal price;
    int visibility;
    int position;

    public CookingInstr(int cat_id, String name, BigDecimal price, int visibility, int position)
    {
        this.cat_id = cat_id;
        this.name = name;
        this.price = price;
        this.visibility = visibility;
        this.position = position;

        this.id = count;
        count++;
    }

    public CookingInstr(int id, int cat_id, String name, BigDecimal price, int visibility, int position)
    {
        this(cat_id, name, price, visibility, position);
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

    public void setCatId(int cat_id) {
        this.cat_id = cat_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
