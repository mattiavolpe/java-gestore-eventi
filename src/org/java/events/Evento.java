package org.java.events;

import java.time.LocalDate;

public class Evento extends DateConverter {
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
		LocalDate tempDate = getConvertedInputDate(date);
		
		if (tempDate.isBefore(LocalDate.now()))
			throw new Exception("The date can't be in the past");
		
		this.date = tempDate;
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
	
	public void reserve(int places) throws Exception {		
		if (getTotalPlaces() < (getReservedPlaces() + places))
			throw new Exception(
					getTotalPlaces() - getReservedPlaces() == 0
					? "There are no more available places"
					: "Still available places: " + (getTotalPlaces() - getReservedPlaces())
			);
		
		if (getDate().isBefore(LocalDate.now()))
			throw new Exception("The event is already past");
		
		setReservedPlaces(getReservedPlaces() + places);
	}
	
	public void remove(int places) throws Exception {
		if (getDate().isBefore(LocalDate.now()))
			throw new Exception("The event is already past");
		setReservedPlaces(getReservedPlaces() - places);
	}
	
	protected String getFormattedDateForOutput() {
		int day = date.getDayOfMonth();
		int month = date.getMonthValue();
		int year = date.getYear();
		return String.format("%02d", day) + "-" + String.format("%02d", month) + "-" + String.format("%04d", year);
	}
	
	@Override
	public String toString() {
		return getFormattedDateForOutput() + " - " + getTitle();
	}
}
