package org.java.events;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

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
	
	public String eventsAtDate(String date) throws Exception {		
		try {
			LocalDate targetDate = getConvertedInputDate(date);
			List<Evento> eventsAtGivenDate = events.stream().filter(event -> targetDate.isEqual(event.getDate())).toList();
			return this.toString(eventsAtGivenDate);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		return "";
	}
	
	public int countEvents() {
		return events.size();
	}
	
	public void clearList() {
		events.clear();
	}
	
	public BigDecimal avgEventsPrice() {
		AtomicReference<BigDecimal> counter = new AtomicReference<>(BigDecimal.ZERO);
		AtomicReference<BigDecimal> avg = new AtomicReference<>(BigDecimal.ZERO);
		
		events.forEach(event -> {
			if (event instanceof Concerto) {
				Concerto concert = (Concerto) event;
				avg.updateAndGet(oldAvg -> oldAvg.add(concert.getPrice()));
				counter.updateAndGet(oldCounter -> oldCounter.add(new BigDecimal(1)));
			} else if (event instanceof Spettacolo) {
				Spettacolo show = (Spettacolo) event;
				avg.updateAndGet(oldAvg -> oldAvg.add(show.getPrice()));
				counter.updateAndGet(oldCounter -> oldCounter.add(new BigDecimal(1)));
			}
		});
		
		return avg.updateAndGet(oldAvg -> oldAvg.divide(counter.get()));
	}
	
	public BigDecimal avgConcertsPrice() {
		AtomicReference<BigDecimal> counter = new AtomicReference<>(BigDecimal.ZERO);
		AtomicReference<BigDecimal> avg = new AtomicReference<>(BigDecimal.ZERO);
		
		events.forEach(event -> {
			if (event instanceof Concerto) {
				Concerto concert = (Concerto) event;
				avg.updateAndGet(oldAvg -> oldAvg.add(concert.getPrice()));
				counter.updateAndGet(oldCounter -> oldCounter.add(new BigDecimal(1)));
			}
		});
		
		return avg.updateAndGet(oldAvg -> oldAvg.divide(counter.get()));
	}
	
	public BigDecimal avgShowsPrice() {
		AtomicReference<BigDecimal> counter = new AtomicReference<>(BigDecimal.ZERO);
		AtomicReference<BigDecimal> avg = new AtomicReference<>(BigDecimal.ZERO);
		
		events.forEach(event -> {
			if (event instanceof Spettacolo) {
				Spettacolo show = (Spettacolo) event;
				avg.updateAndGet(oldAvg -> oldAvg.add(show.getPrice()));
				counter.updateAndGet(oldCounter -> oldCounter.add(new BigDecimal(1)));
			}
		});
		
		return avg.updateAndGet(oldAvg -> oldAvg.divide(counter.get()));
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
	
	public String toString(List<Evento> eventsAtDate) {
		List<Evento> orderedEventsAtDate = eventsAtDate.stream().sorted((eventA, eventB) -> eventA.getDate().compareTo(eventB.getDate())).toList();
		Iterator<Evento> orderedEventsAtDateIterator = orderedEventsAtDate.iterator();
		String eventsResume = "\n----------\n"
				+ "Events found for the date:\n"
				+ "----------\n";
		
		while (orderedEventsAtDateIterator.hasNext()) {
			Evento event = orderedEventsAtDateIterator.next();
			eventsResume += event.getFormattedDateForOutput() + " - " + event.getTitle() + "\n";
		}
		
		if (eventsAtDate.size() == 0)
			eventsResume += "No events found for the date";
		
		return eventsResume;
	}
}
