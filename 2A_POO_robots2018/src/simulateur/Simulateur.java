package simulateur;

import java.util.ArrayDeque;
import java.util.Deque;

public class Simulateur implements Cloneable{
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
		 * Cette m�thode va prendre le prochain �venement � faire, maj le moment ou le robot sera libre et lance l'execution de l'�venement
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

	@Override
	public String toString() {
		return "Simulateur [dateSimulation=" + dateSimulation + ", events=" + events + ", currentEvent=" + currentEvent
				+ "]";
	}
	
	public Object clone() {
		Object o = null;
		try {
			// On r�cup�re l'instance � renvoyer par l'appel de la 
			// m�thode super.clone()
			o = super.clone();
		} catch(CloneNotSupportedException cnse) {
			// Ne devrait jamais arriver car nous impl�mentons 
			// l'interface Cloneable
			cnse.printStackTrace(System.err);
		}
		// on renvoie le clone
		return o;
	}
	
	
	
}
