package org.java.events;

import java.time.LocalDate;

public class Evento {
	private String title;
	private LocalDate date;
	private int totalPlaces;
	private int reservedPlaces;
	
	public Evento(String title, String date, int totalPlaces) throws Exception {
		setTitle(title);
		setDate(date);
		setTotalPlaces(totalPlaces);
		setReservedPlaces(0);
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(String date) throws Exception {
		short[] dateContainer = new short[3];
		dateContainer[0] = Short.valueOf(date.substring(0, date.indexOf("-")));
		dateContainer[1] = Short.valueOf(date.substring(date.indexOf("-") + 1, date.lastIndexOf("-")));
		dateContainer[2] = Short.valueOf(date.substring(date.lastIndexOf("-") + 1));
		this.date = LocalDate.of(dateContainer[2], dateContainer[1], dateContainer[0]);
		
		if (getDate().isBefore(LocalDate.now()))
			throw new Exception("The date can't be in the past");
	}
	
	public int getTotalPlaces() {
		return totalPlaces;
	}
	
	private void setTotalPlaces(int totalPlaces) throws Exception {
		if (totalPlaces <= 0)
			throw new Exception("There has to be at least 1 place");
		
		this.totalPlaces = totalPlaces;
	}
	
	public int getReservedPlaces() {
		return reservedPlaces;
	}
	
	private void setReservedPlaces(int reservedPlaces) {
		this.reservedPlaces = reservedPlaces;
	}
	
	public void reserve() {
		setReservedPlaces(getReservedPlaces() + 1);
	}
	
	public void remove() {
		setReservedPlaces(getReservedPlaces() - 1);
	}
	
	@Override
	public String toString() {
		return getDate() + " - " + getTitle();
	}
}
