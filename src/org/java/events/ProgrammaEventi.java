package org.java.events;

import java.util.ArrayList;
import java.util.List;

public class ProgrammaEventi {
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
}
