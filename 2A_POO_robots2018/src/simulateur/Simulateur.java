package simulateur;

import java.util.ArrayDeque;
import java.util.Deque;

public class Simulateur {
	private long dateSimulation; //Date courante de simulation
	Deque <Evenement> events=new ArrayDeque<Evenement>(); // Liste des evenements
	private Evenement currentEvent;
	
	public void ajouteEvenement(Evenement e) {
		if (events.peekFirst()!=null) {
			Evenement dernier = events.getLast();
			this.setDateSimulation(dernier.getDate() + e.getDuree());
		}
		else {
			this.setDateSimulation(0);
		}
		events.add(e);
	}
	
	public void execEvenement() {
		/**
		 * Cette méthode va prendre le prochain évenement à faire, maj le moment ou le robot sera libre et lance l'execution de l'évenement
		 */
		currentEvent = events.pollFirst();
//		System.out.println(currentEvent.toString());
		incrementeDate();
		currentEvent.getRobot().setDateWhereFree(currentEvent.getDuree());
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
