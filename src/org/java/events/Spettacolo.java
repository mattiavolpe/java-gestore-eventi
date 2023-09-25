package org.java.events;

import java.math.BigDecimal;

public class Spettacolo extends Evento {
	private BigDecimal price;
	
	public Spettacolo(String title, String date, int totalPlaces, BigDecimal price) throws Exception  {
		super(title, date, totalPlaces);
		setPrice(price);
	}
	
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) throws Exception {
		if (price.doubleValue() < 0)
			throw new Exception("Price can't be negative");
		this.price = price;
	}
	
	public String getFormattedPrice() {
		return String.format("%.02f", getPrice()) + "€";
	}
	
	@Override
	public String toString() {
		return super.getFormattedDateForOutput() + " - " + getTitle() + " - " + getFormattedPrice();
	}
}
