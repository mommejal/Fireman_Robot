package simulateur;

import java.util.Deque;
import java.util.Queue;

public class Simulateur {
	private long dateSimulation; //Date courante de simulation
	Deque <Evenement> events; // Liste des evenements
	private Evenement currentEvent;
	
	public void ajouteEvenement(Evenement e) {
		if (!events.isEmpty()) {
			Evenement dernier = events.getLast();
			this.setDateSimulation(dernier.getDate() + e.getDuree());
		}
		else {
			this.setDateSimulation(0);
		}
		events.add(e);
	}
	
	public void execEvenement() {
		currentEvent = events.pollFirst();
		incrementeDate();
		currentEvent.execute();
	}
	
	private void incrementeDate() {
		dateSimulation += currentEvent.getDuree();
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

	public Queue<Evenement> getEvents() {
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
