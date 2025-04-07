package com.example.demo.entities;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="payment_service")
public class Payment {
	
	@Id
    private String id;
	private String userId;
    private double amount;
    private String cardNumber;
    private String CVV;
    private String expirationDate;
    private String status;
    private List<Item> items;
    
	
	public Payment(String userId,double amount, String cardNumber, String CVV, String expirationDate, String status) {
		super();
		this.userId =userId;
		this.amount = amount;
		this.cardNumber = cardNumber;
		this.CVV = CVV;
		this.expirationDate = expirationDate;
		this.status = status;
	}

	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	 public String getCardNumber() {
	        return cardNumber;
	    }

	    public void setCardNumber(String cardNumber) {
	        this.cardNumber = cardNumber;
	    }

	    public String getCVV() {
	        return CVV;
	    }

	    public void setCVV(String cvv) {
	        this.CVV = cvv;
	    }

	    public String getExpirationDate() {
	        return expirationDate;
	    }

	    public void setExpirationDate(String expirationDate) {
	        this.expirationDate = expirationDate;
	    }

	    public String getStatus() {
	        return status;
	    }

	    public void setStatus(String status) {
	        this.status = status;
	    }
		@Override
		public String toString() {
			return "Payment [paymentId=" + id + ",userId=\" + userId + \", amount=" + amount
					+ ", cardNumber=" + cardNumber + ", CVV=" + CVV + ", expirationDate=" + expirationDate + ", status="
					+ status + "]";
		}
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}

		public List<Item> getItems() {
			return items;
		}

		public void setItems(List<Item> items) {
			this.items = items;
		}
	
	
}