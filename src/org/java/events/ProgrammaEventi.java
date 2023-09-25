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
		List<Evento> orderedEvents = events.stream().sorted((eventA, eventB) -> eventA.getDate().compareTo(eventB.getDate())).toList();
		Iterator<Evento> orderedEventsIterator = orderedEvents.iterator();
		String eventsResume = "\n----------\n"
				+ "Events program's RESUME:\n"
				+ "----------\n";
		
		while (orderedEventsIterator.hasNext()) {
			Evento event = orderedEventsIterator.next();
			eventsResume += event.getFormattedDateForOutput() + " - " + event.getTitle() + "\n";
		}
		
		if (countEvents() == 0)
			eventsResume += "No events in the program";
		
		return eventsResume;
	}
}
