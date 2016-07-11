package com.appdynamics.rest;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CartItem {

	// No Argument Constructor
	public CartItem() {
	}

	// Argument Constructor
	public CartItem(String id, String title, String imagePath,String itemId,String price) {
		super();
		this.id = id;
		this.title = title;
		this.imagePath = imagePath;
		this.itemId = itemId;
		this.price = price;
	}

	// Private fields
	@XmlElement(name="id")
	private String id;

	@XmlElement(name="title")
	private String title;

	@XmlElement(name="imagePath")
	private String imagePath;

	@XmlElement(name="itemId")
	private String itemId;

	@XmlElement(name="price")
	private String price;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getItemid() {
		return itemId;
	}

	public void setItemid(String itemId) {
		this.itemId = itemId;
	}
	
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
}
