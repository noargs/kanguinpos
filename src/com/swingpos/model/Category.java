package com.swingpos.model;

import java.io.Serializable;

public class Category implements Serializable {

	private static int count = 1;

	private int id;
	private String name;
	private String image;
	private int r;
	private int g;
	private int b;
	private int position;

	public Category(String name, String image, int r, int g, int b, int position)
	{
		this.name = name;
		this.image = image;
		this.r = r;
		this.g = g;
		this.b = b;
		this.position = position;

		this.id = count;
		count++;
	}

	public Category(int id, String name, String image, int r, int g, int b, int position)
	{
		this(name, image, r, g, b, position);

		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String category) {
		this.name = category;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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

}

