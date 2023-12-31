package org.java.events;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
	public static Scanner sc = new Scanner(System.in);
	public static ProgrammaEventi programmaEventi = null;
	
	public static void main(String[] args) {
//		--------------------------------------------------------------------
//		HARD-CODED TEST
//		--------------------------------------------------------------------
		System.out.println("\n********************\n");
		System.out.println("HARD-CODED TEST");
		
		programmaEventi = new ProgrammaEventi("Default test program title");
		
//		ADD EVENTS
		try {
			programmaEventi.addEvent(new Evento("Evento test 1", "03-10-2024", 30));
			programmaEventi.addEvent(new Concerto("Concerto test 1", "12-12-2030", 60000, "03:12", BigDecimal.valueOf(70.50)));
			programmaEventi.addEvent(new Evento("Evento test 2", "01-01-2024", 60));
			programmaEventi.addEvent(new Spettacolo("Spettacolo test 1", "25-06-2024", 500, BigDecimal.valueOf(40)));
			programmaEventi.addEvent(new Evento("Evento test 3", "01-07-2028", 45));
			programmaEventi.addEvent(new Evento("Evento test 4", "02-01-2024", 89));
			programmaEventi.addEvent(new Spettacolo("Spettacolo test 2", "25-08-2027", 5000, BigDecimal.valueOf(400)));
			programmaEventi.addEvent(new Concerto("Concerto test 2", "12-12-2035", 30000, "21:45", BigDecimal.valueOf(700.50)));
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		System.out.println("\nGeneric average price: " + String.format("%.02f", programmaEventi.avgEventsPrice()) + "€");
		System.out.println("Concerts average price: " + String.format("%.02f", programmaEventi.avgConcertsPrice()) + "€");
		System.out.println("Shows average price: " + String.format("%.02f", programmaEventi.avgShowsPrice()) + "€");
		
//		LOOK FOR A DATE
		try {
			System.out.print(programmaEventi.eventsAtDate("12-12-2030"));
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		System.out.println("----------");
		
//		COUNTS THE EVENTS
		System.out.println("Count: " + programmaEventi.countEvents());
		System.out.println("----------");
		
//		SHOWS THE EVENT'S PROGRAM
		System.out.println(programmaEventi);
		System.out.println("----------");
		
//		CLEARS THE LIST
		programmaEventi.clearList();
		System.out.println("Cleared list: " + programmaEventi);
		System.out.println("----------");
		
		System.out.println("END OF THE HARD-CODED TEST");
		System.out.println("\n********************\n");
//		--------------------------------------------------------------------
//		END OF HARD-CODED TEST
//		--------------------------------------------------------------------
		
		String eventsProgramTitle = "Default program title";
		
		System.out.print("Enter the events program's title: ");
		eventsProgramTitle = sc.nextLine();
		
		programmaEventi = new ProgrammaEventi(eventsProgramTitle);
		
		byte choice = 0;
		
		do {
			System.out.println("\nYou want to insert:\n"
					+ "[1] Generic event\n"
					+ "[2] Concert\n"
					+ "[3] Show\n"
					+ "[0] Nothing"
					);
			
			try {
				choice = Byte.valueOf(sc.nextLine());				
			} catch (Exception e) {
				System.err.println("Enter a valid number");
			}
			
			if (choice == 1)
				insertGenericEvent();
			else if (choice == 2)
				insertConcert();
			else if (choice == 3)
				insertShow();
		} while (choice != 0);
		
		sc.close();
		
		System.out.println(programmaEventi);
	}
	
	public static void insertGenericEvent() {

		Evento evento = null;
		
		System.out.print("\nEnter the event's title: ");
		String title = sc.nextLine();
		
		System.out.print("\nEnter the event's date in the format 'dd-mm-yyyy': ");
		String date = sc.nextLine();
		
		try {
			System.out.print("\nEnter the event's total places number: ");
			int totalPlaces = Integer.valueOf(sc.nextLine());
		
			evento = new Evento(title, date, totalPlaces);
			
			programmaEventi.addEvent(evento);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		if (evento != null) {
			System.out.println("\nYou just created the following event: \n----------\n" + evento + "\n----------");
			
			lookForReservation(evento);			
		}
	}
	
	public static void insertConcert() {

		Concerto concerto = null;
		
		System.out.print("\nEnter the concert's title: ");
		String concertTitle = sc.nextLine();
		
		System.out.print("\nEnter the concert's date in the format 'dd-mm-yyyy': ");
		String concertDate = sc.nextLine();
		
		try {
			System.out.print("\nEnter the concert's total places number: ");
			int concertTotalPlaces = Integer.valueOf(sc.nextLine());
			
			System.out.print("\nEnter the concert's time in the format 'hh:mm': ");
			String concertTime = sc.nextLine();
			
			System.out.print("\nEnter the concert's price: ");
			BigDecimal concertPrice = new BigDecimal(sc.nextLine());
		
			concerto = new Concerto(concertTitle, concertDate, concertTotalPlaces, concertTime, concertPrice);
			
			programmaEventi.addEvent(concerto);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		if (concerto != null) {
			System.out.println("\nYou just created the following concert: \n----------\n" + concerto + "\n----------");
		
			lookForReservation(concerto);
		}
	}
	
	public static void insertShow() {

		Spettacolo spettacolo = null;
		
		System.out.print("\nEnter the show's title: ");
		String showTitle = sc.nextLine();
		
		System.out.print("\nEnter the show's date in the format 'dd-mm-yyyy': ");
		String showDate = sc.nextLine();
		
		try {
			System.out.print("\nEnter the show's total places number: ");
			int showTotalPlaces = Integer.valueOf(sc.nextLine());
			
			System.out.print("\nEnter the show's price: ");
			BigDecimal showPrice = new BigDecimal(sc.nextLine());
		
			spettacolo = new Spettacolo(showTitle, showDate, showTotalPlaces, showPrice);
			
			programmaEventi.addEvent(spettacolo);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		if (spettacolo != null) {
			System.out.println("\nYou just created the following show: \n----------\n" + spettacolo + "\n----------");
		
			lookForReservation(spettacolo);
		}
	}
	
	public static void lookForReservation(Evento event) {
		
		int placesToReserve = 0;
		int reservedPlaces = 0;
		
		do {
			try {
				System.out.print("\nEnter the number of places you want to RESERVE (enter 0 if you don't want to make a reservation): ");
				placesToReserve = Integer.valueOf(sc.nextLine());
				
				if (placesToReserve < 0)
					System.err.println("You can't reserve less than 1 place");
				else if (placesToReserve > 0) {
					event.reserve(placesToReserve);
					reservedPlaces += placesToReserve;
					
					System.err.println("\nYou reserved a total of " + reservedPlaces + " places");
					System.err.println("Still " + (event.getTotalPlaces() - event.getReservedPlaces()) + " places available");
				}
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		} while (placesToReserve != 0 && (event.getTotalPlaces() - event.getReservedPlaces() > 0));
		
		if (placesToReserve != 0)
			System.err.println("\nNo more available places to reserve");
		else
			System.err.println("\nYou reserved a total of " + reservedPlaces + " places");
		
		int placesToRemove = 0;
		
		do {
			if (reservedPlaces > 0) {
				try {
					System.out.print("\nEnter the number of places you want to REMOVE (enter 0 if you don't want to remove any place): ");
					placesToRemove = Integer.valueOf(sc.nextLine());
					
					if (placesToRemove < 0)
						System.err.println("You can't remove less than 1 place");
					else if (placesToRemove > 0 && reservedPlaces >= placesToRemove) {
						event.remove(placesToRemove);
						reservedPlaces -= placesToRemove;
						
						System.err.println("\nYou successfully removed " + placesToRemove + " places");
						System.err.println("\nYou still have a total of " + reservedPlaces + " places");
						System.err.println("Still " + (event.getTotalPlaces() - event.getReservedPlaces()) + " places available");
					} else if (placesToRemove > 0){
						System.err.println("\nYou tried to remove " + placesToRemove + " places, but you only have " + reservedPlaces + " places still reserved");
					}
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
		} while (placesToRemove != 0 && reservedPlaces > 0);
		
		if (placesToRemove != 0)
			System.err.println("\nNo more reserved places to remove");
		else if (reservedPlaces != 0)
			System.err.println("\nYou reserved a total of " + reservedPlaces + " places");
	}
}
