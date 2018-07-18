package com.punjabifashion.beans;

import java.io.InputStream;

public class Product {
	private int id;
	private String title;
	private String category;
	private String fit;
	private String name;
	private String description;
	private Double mrp;
	private Double discount;
	private Double avgRating;
	private int totalRating;
	private String[] sizes;
	private String colors;
	private String gender;
	private boolean stitched;
	private InputStream photo;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getFit() {
		return fit;
	}
	public void setFit(String fit) {
		this.fit = fit;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Double getMrp() {
		return mrp;
	}
	public void setMrp(Double mrp) {
		this.mrp = mrp;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public Double getAvgRating() {
		return avgRating;
	}
	public void setAvgRating(Double avgRating) {
		this.avgRating = avgRating;
	}
	public int getTotalRating() {
		return totalRating;
	}
	public void setTotalRating(int totalRating) {
		this.totalRating = totalRating;
	}
	public String[] getSizes() {
		return sizes;
	}
	public void setSizes(String[] sizes) {
		this.sizes = sizes;
	}
	public String getColors() {
		return colors;
	}
	public void setColors(String colors) {
		this.colors = colors;
	}
	public String getGender() {
		return gender;
	}
	public void setGenders(String gender) {
		this.gender = gender;
	}
	public boolean isStitched() {
		return stitched;
	}
	public void setStitched(boolean stitched) {
		this.stitched = stitched;
	}
	public InputStream getPhoto() {
		return photo;
	}
	public void setPhoto(InputStream photo) {
		this.photo = photo;
	}
	
	
}
