package com.appdynamics.rest;

//Product Class
public class Item {
 
	//No Argument Constructor
	public Item() {
	}
	
	//Argument Constructor
	public Item(String id, String title, String imagePath, String price) {
		super();
		this.id = id;
		this.title = title;
		this.imagePath = imagePath;
		this.price = price;
	}
	
	//Private fields
	private String id;
	private String title;
	private String imagePath;
	private String price;
	
	//Getter and Setter of Id
	public String getId() {
		return id;
	}

	public void setId(String string) {
		this.id = string;
	}
	
	//Getter and Setter of title
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	//Getter and Setter of imagePath
	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	//Getter and Setter of price
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

}