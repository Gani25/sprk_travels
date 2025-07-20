package com.sprk.sprk_travels.entity;

public class Hotel {
	
	private int propertyId;
	
	private String propertyName;
	
	private double propertyPrice;
	
	private String propertyDescription;
	
	private String propertyUrl;

	public Hotel() {
		// TODO Auto-generated constructor stub
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	

	public double getPropertyPrice() {
		return propertyPrice;
	}

	public void setPropertyPrice(double propertyPrice) {
		this.propertyPrice = propertyPrice;
	}

	public String getPropertyDescription() {
		return propertyDescription;
	}

	public void setPropertyDescription(String propertyDescription) {
		this.propertyDescription = propertyDescription;
	}

	public String getPropertyUrl() {
		return propertyUrl;
	}

	public void setPropertyUrl(String propertyUrl) {
		this.propertyUrl = propertyUrl;
	}

	public int getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(int propertyId) {
		this.propertyId = propertyId;
	}

	@Override
	public String toString() {
		return "Hotel [propertyId=" + propertyId + ", propertyName=" + propertyName + ", propertyPrice=" + propertyPrice
				+ ", propertyDescription=" + propertyDescription + ", propertyUrl=" + propertyUrl + "]";
	}
	
	
	
}
