package com.beerhouse.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Valid
public class Beers{
	
	@Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
	
	@NotEmpty(message = "Field NAME is required.")
	@Column(name = "name")
	private String name;
	
	@NotEmpty(message = "Field INGREDIENTS is required.")
	@Column(name = "ingredients")
	private String ingredients;
	
	@NotEmpty(message = "Field ALCOHOL CONTENT is required.")
	@Column(name = "alcohol_content")
	private String alcoholContent;
	
	@NotNull(message = "Field PRICE is required.")
	@Column(name = "price")
	private Double price;
	
	@NotEmpty(message = "Field CATEGORY is required.")
	@Column(name = "category")
	private String category;
	
	public Beers() {
		
	}
	
	public Beers(String name, String ingredients, String alcoholContent, Double price, String category) {
		super();
		this.name = name;
		this.ingredients = ingredients;
		this.alcoholContent = alcoholContent;
		this.price = price;
		this.category = category;
	}

	public String getId() {
		return id;
	}
	
	@Size(max=50)
	public String getName() {
		return name;
	}

	@Size(max=200)
	public String getIngredients() {
		return ingredients;
	}

	@Size(max=30)
	public String getAlcoholContent() {
		return alcoholContent;
	}

	@DecimalMin("0.01")
	@DecimalMax("99999999.99")
	public Double getPrice() {
		return price;
	}

	@Size(max=50)
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
