package org.java.events;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		Evento evento = null;
		
		System.out.print("Enter the event's title: ");
		String title = sc.nextLine();
		
		System.out.print("\nEnter the event's date in the format 'dd-mm-yyyy': ");
		String date = sc.nextLine();
		
		try {
			System.out.print("\nEnter the event's total places number: ");
			int totalPlaces = Integer.valueOf(sc.nextLine());
		
			evento = new Evento(title, date, totalPlaces);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		System.out.println("\nYou just created the following event: \n----------\n" + evento + "\n----------");
		
		int placesToReserve = 0;
		int reservedPlaces = 0;
		
		do {
			try {
				System.out.print("\nEnter the number of places you want to RESERVE (enter 0 if you don't want to make a reservation): ");
				placesToReserve = Integer.valueOf(sc.nextLine());
				
				if (placesToReserve < 0)
					System.err.println("You can't reserve less than 1 place");
				else if (placesToReserve > 0) {
					evento.reserve(placesToReserve);
					reservedPlaces += placesToReserve;
					
					System.err.println("\nYou reserved a total of " + reservedPlaces + " places");
					System.err.println("Still " + (evento.getTotalPlaces() - evento.getReservedPlaces()) + " places available");
				}
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		} while (placesToReserve != 0 && (evento.getTotalPlaces() - evento.getReservedPlaces() > 0));
		
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
						evento.remove(placesToRemove);
						reservedPlaces -= placesToRemove;
						
						System.err.println("\nYou successfully removed " + placesToRemove + " places");
						System.err.println("\nYou still have a total of " + reservedPlaces + " places");
						System.err.println("Still " + (evento.getTotalPlaces() - evento.getReservedPlaces()) + " places available");
					} else if (placesToRemove > 0){
						System.err.println("\nYou tried to remove " + placesToRemove + " places, but you only have " + reservedPlaces + " places still reserved");
					}
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
		} while (placesToRemove != 0 && reservedPlaces > 0);
		
		if (placesToRemove != 0)
			System.err.println("\nNo more available places");
		else
			System.err.println("\nYou reserved a total of " + reservedPlaces + " places");
		
		sc.close();
	}
}
