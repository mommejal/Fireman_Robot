package simulateur;

import java.util.ArrayList;

import carte.Carte;
import carte.Case;
import cheminoptimal.cheminoptimal;
import robot.Robot;

public class ChefPompier {

	/**
	 * LA classe chef des pompiers va servir à diriger les différents robots au
	 * travers des incendies
	 */

	private Carte carte;
	private SimulateurBis simulateur;
	private ArrayList<Case> incendies = new ArrayList<Case>();
	private int nbOrdres = 0;
	

	public void assignRobot() {
		for (Robot rob : carte.getRobots()) {
			if (rob.isFree(simulateur.getDateSimulation()) && (rob.getVolume() > 0)) {
				cheminoptimal co = new cheminoptimal(rob);
				co.travelTo(incendies.get((int) Math.random() * carte.getNbIncendies()));// L'incendie est pris au  hasard
				simulateur.ajouteEvenement(new Intervention(rob));
				System.out.println(rob.getSimulateur().getEvents())
																							
			} else if (rob.isFree(simulateur.getDateSimulation()) && (rob.getVolume() == 0)) {
				rob.goRemplir();
			}
			break;
		}
	}

	public void updateIncendies() {
		/**
		 * Relit la carte pour informer le chef des robots des endroits ou des incendies
		 * sont en cours
		 */
		incendies.clear();
		for (Case cur : carte.getMap()) {
			if (cur.getIncendie() > 0) {
				incendies.add(cur);
			}
		}
	}
	
	public void faireLeChef(long pas) {
		/**
		 * Dirige les robots selon la stratégie élémentaire tous les pas secondes
		 */
		if (simulateur.getDateSimulation()>=pas*nbOrdres) {
			updateIncendies();
			assignRobot();
			nbOrdres++;
		}
	}

	public Carte getCarte() {
		return carte;
	}

	public void setCarte(Carte carte) {
		this.carte = carte;
	}

	public SimulateurBis getSimulateur() {
		return simulateur;
	}

	public void setSimulateur(SimulateurBis simulateur) {
		this.simulateur = simulateur;
	}

	public ArrayList<Case> getIncendies() {
		return incendies;
	}

	public void setIncendies(ArrayList<Case> incendies) {
		this.incendies = incendies;
	}
	
	
	
	
}
