package com.customer.demo.dto;

import java.time.LocalDate;

public class CardDTO {
	
	Integer cardId;
	String cardNumber;
	LocalDate expiryDate;
	CustomerDTO customer;
	
	public Integer getCardId() {
		return cardId;
	}
	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public LocalDate getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}
	public CustomerDTO getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerDTO customer) {
		this.customer = customer;
	}
	
	

}
