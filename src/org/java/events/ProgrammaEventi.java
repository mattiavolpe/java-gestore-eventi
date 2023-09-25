package org.java.events;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProgrammaEventi extends DateConverter {
	private String title;
	private List<Evento> events;
	
	public ProgrammaEventi(String title) {
		setTitle(title);
		events = new ArrayList<>();
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void addEvent(Evento event) {
		events.add(event);
	}
	
	public List<Evento> eventsAtDate(String date) throws Exception {		
		try {
			LocalDate targetDate = getConvertedInputDate(date);
			List<Evento> eventsAtGivenDate = events.stream().filter(event -> targetDate.isEqual(event.getDate())).toList();
			return eventsAtGivenDate;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		return events;
	}
	
	public int countEvents() {
		return events.size();
	}
	
	public void clearList() {
		events.clear();
	}
	
	@Override
	public String toString() {
		Iterator<Evento> eventsIterator = events.iterator();
		String eventsResume = "";
		
		while (eventsIterator.hasNext())
			eventsResume += eventsIterator.next().toString() + "\n";			
		
		return eventsResume;
	}
}
