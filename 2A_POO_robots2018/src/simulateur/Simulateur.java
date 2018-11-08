package simulateur;

import java.util.ArrayDeque;
import java.util.Deque;

public class Simulateur {
	private long dateSimulation; //Date courante de simulation
	Deque <Evenement> events=new ArrayDeque<Evenement>(); // Liste des evenements
	private Evenement currentEvent;
	
	public void ajouteEvenement(Evenement e) {
//		if (events.peekFirst()!=null) {
//			Evenement dernier = events.getLast();
//			e.setDate(dernier.getDate() + e.getDuree());
//		}
//		else {
//			this.setDateSimulation(0);
//		}
		events.addFirst(e);
	}
	
	public void execEvenement() {
		
		currentEvent = events.pollLast();
//		System.out.println(currentEvent.toString());
		incrementeDate();
		currentEvent.execute();
	}
	
	private void incrementeDate() {
		this.dateSimulation += currentEvent.getDuree();
	}
	
	public boolean simulationTerminee() {
		return (events.isEmpty());
	}

	public long getDateSimulation() {
		return dateSimulation;
	}

	public void setDateSimulation(long dateSimulation) {
		this.dateSimulation = dateSimulation;
	}

	public Deque<Evenement> getEvents() {
		return events;
	}

	public void setEvents(Deque<Evenement> events) {
		this.events = events;
	}

	public Evenement getCurrentEvent() {
		return currentEvent;
	}

	public void setCurrentEvent(Evenement currentEvent) {
		this.currentEvent = currentEvent;
	}
	
	
	
}
