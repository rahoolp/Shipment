package com.gap.order.shipmentOrder;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Order implements Serializable{

	private String orderId;
	private String termsOfSale;
	private String countryOfOrigin;
	private String destinationCountry;
	
	public String getOrderId() {
		return orderId;
	}
	public String getTermsOfSale() {
		return termsOfSale;
	}
	public String getCountryOfOrigin() {
		return countryOfOrigin;
	}
	public String getDestinationCountry() {
		return destinationCountry;
	}

}
