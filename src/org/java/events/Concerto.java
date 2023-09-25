package org.java.events;

import java.math.BigDecimal;
import java.time.LocalTime;

public class Concerto extends Evento {
	private LocalTime time;
	private BigDecimal price;
	
	public Concerto(String title, String date, int totalPlaces, String time, BigDecimal price) throws Exception  {
		super(title, date, totalPlaces);
		setTime(time);
		setPrice(price);
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(String time) throws Exception {
		LocalTime tempTime = convertInputTime(time);
		
		this.time = tempTime;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) throws Exception {
		if (price.doubleValue() < 0)
			throw new Exception("Price can't be negative");
		this.price = price;
	}
	
	private LocalTime convertInputTime(String time) throws Exception {
		byte[] timeContainer = new byte[2];
		
//		USING try / catch TO ALSO CATCH ERRORS ON Short.valueOf STATEMENTS
		try {
			timeContainer[0] = Byte.valueOf(time.substring(0, time.indexOf(":")));
			timeContainer[1] = Byte.valueOf(time.substring(time.indexOf(":") + 1));		
			return LocalTime.of(timeContainer[0], timeContainer[1]);
		} catch (Exception e) {
			throw new Exception("Insert a valid time");
		}
	}
	
	public String getFormattedPrice() {
		return String.format("%.02f", getPrice()) + "€";
	}
	
	@Override
	public String toString() {
		return super.getFormattedDateForOutput() + " at " + getTime() + " - " + getTitle() + " - " + getFormattedPrice();
	}
}
