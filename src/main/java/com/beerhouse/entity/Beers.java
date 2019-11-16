package com.beerhouse.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Beers{
	
	@Id
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "ingredients")
	private String ingredients;
	
	@Column(name = "alcohol_content")
	private String alcoholContent;
	
	@Column(name = "price")
	private Double price;
	
	@Column(name = "category")
	private String category;
	

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getIngredients() {
		return ingredients;
	}

	public String getAlcoholContent() {
		return alcoholContent;
	}

	public Double getPrice() {
		return price;
	}

	public String getCategory() {
		return category;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public void setAlcoholContent(String alcoholContent) {
		this.alcoholContent = alcoholContent;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	
}
