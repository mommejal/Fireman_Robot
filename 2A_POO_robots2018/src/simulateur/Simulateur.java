package simulateur;

import java.util.Queue;

public class Simulateur {
	private long dateSimulation; //Date courante de simulation
	Queue <Evenement> events; // Liste des evenements
	private Evenement currentEvent;
	
	public void ajouteEvenement(Evenement e) {
		events.add(e);
	}
	
	public void execEvenement() {
		currentEvent = events.poll();
		incrementeDate();
		currentEvent.execute();
	}
	
	private void incrementeDate() {
		dateSimulation += currentEvent.getDate();
	}
	
	private boolean simulationTerminee() {
		return (events.isEmpty());
	}
	
}
