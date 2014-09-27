package com.gap.supplychain.order.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Order implements Serializable {
	
	public Order() {
		
	}
	
	public Order(String termsOfSale, String countryOfOrigin,
			String destinationCountry) {
		super();
		this.setOrderId(orderId);
		this.setTermsOfSale(termsOfSale);
		this.setCountryOfOrigin(countryOfOrigin);
		this.setDestinationCountry(destinationCountry);
	}
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getTermsOfSale() {
		return termsOfSale;
	}

	public void setTermsOfSale(String termsOfSale) {
		this.termsOfSale = termsOfSale;
	}

	public String getCountryOfOrigin() {
		return countryOfOrigin;
	}

	public void setCountryOfOrigin(String countryOfOrigin) {
		this.countryOfOrigin = countryOfOrigin;
	}

	public String getDestinationCountry() {
		return destinationCountry;
	}

	public void setDestinationCountry(String destinationCountry) {
		this.destinationCountry = destinationCountry;
	}

	private String orderId;
	private String termsOfSale;
	private String countryOfOrigin;
	private String destinationCountry;
	
	
}
