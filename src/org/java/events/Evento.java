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
		LocalDate tempDate = convertInputDate(date);
		
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
		
		setReservedPlaces(getReservedPlaces() + places);
	}
	
	public void remove(int places) {
		
		
		setReservedPlaces(getReservedPlaces() - places);
	}
	
	private LocalDate convertInputDate(String date) throws Exception {
		short[] dateContainer = new short[3];
		
//		USING try / catch TO ALSO CATCH ERRORS ON Short.valueOf STATEMENTS
		try {
			dateContainer[0] = Short.valueOf(date.substring(0, date.indexOf("-")));
			dateContainer[1] = Short.valueOf(date.substring(date.indexOf("-") + 1, date.lastIndexOf("-")));
			dateContainer[2] = Short.valueOf(date.substring(date.lastIndexOf("-") + 1));			
		} catch (Exception e) {
			throw new Exception("Insert a valid date");
		}
		
		return LocalDate.of(dateContainer[2], dateContainer[1], dateContainer[0]);
	}
	
	private String formatDateForOutput() {
		int day = date.getDayOfMonth();
		int month = date.getMonthValue();
		int year = date.getYear();
		return String.format("%02d", day) + "-" + String.format("%02d", month) + "-" + String.format("%04d", year);
	}
	
	@Override
	public String toString() {
		return formatDateForOutput() + " - " + getTitle();
	}
}
