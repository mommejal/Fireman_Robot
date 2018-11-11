package simulateur;

import java.awt.List;
import java.util.ArrayList;
import java.util.Deque;

public class SimulateurBis {
	private long dateSimulation = 0; //Date courante de simulation
	ArrayList<Evenement> events=new ArrayList <Evenement>(); // Liste des evenements
	private Evenement currentEvent;
	private int rangEvent=0;
	

	public void ajouteEvenement(Evenement e) {
//		if (!(events.isEmpty())) {
//			Evenement dernier = events.get(events.size()-1);
//			this.setDateSimulation(dernier.getDate() + e.getDuree());
//		}
//		else {
//			this.setDateSimulation(0);
//		}
//		System.out.println(e);
		events.add(e);
//		System.out.println(events);
	}
	
	public void execEvenement() {
		/**
		 * Cette méthode va prendre le prochain évenement à faire, maj le moment ou le robot sera libre et lance l'execution de l'évenement
		 */
		currentEvent = events.get(rangEvent);
//		System.out.println(currentEvent.toString());
		incrementeDate();
		rangEvent++;
		currentEvent.getRobot().setDateWhereFree(currentEvent.getDuree());
		currentEvent.execute();
	}
	
	private void incrementeDate() {
		dateSimulation += currentEvent.getDuree();
	}
	
	public boolean simulationTerminee() {
//		System.out.println("rang = "+rangEvent);
//		System.out.println("taille = "+events.size());
		return (rangEvent == events.size());
	}

	public long getDateSimulation() {
		return dateSimulation;
	}

	public void setDateSimulation(long dateSimulation) {
		this.dateSimulation = dateSimulation;
	}

	public ArrayList<Evenement> getEvents() {
		return events;
	}

	public void setEvents(ArrayList<Evenement> events) {
		this.events = events;
	}
	
	public int getRangEvent() {
		return rangEvent;
	}
	
	public void setRangEvent(int rangEvent) {
		this.rangEvent = rangEvent;
	}

	public Evenement getCurrentEvent() {
		return currentEvent;
	}

	public void setCurrentEvent(Evenement currentEvent) {
		this.currentEvent = currentEvent;
	}
	
	
	
}
