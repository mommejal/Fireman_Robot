package simulateur;

import java.util.ArrayList;

import carte.Carte;
import carte.Case;
import robot.Robot;

public class ChefPompier {

	/**
	 * LA classe chef des pompiers va servir � diriger les diff�rents robots au
	 * travers des incendies
	 */

	private Carte carte;
	private Simulateur simulateur;
	private ArrayList<Case> incendies;
	private int nbOrdres = 0;

	public void assignRobot() {
		for (Robot rob : carte.getRobots()) {
			if (rob.isFree(simulateur.getDateSimulation()) && (rob.getVolume() > 0)) {
				rob.moveTo(incendies.get((int) Math.random() * carte.getNbIncendies()));// L'incendie est pris au  hasard
				simulateur.ajouteEvenement(new Intervention(rob));
																							
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
		 * Dirige les robots selon la strat�gie �l�mentaire tous les pas secondes
		 */
		if (simulateur.getDateSimulation()>pas*nbOrdres) {
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

	public Simulateur getSimulateur() {
		return simulateur;
	}

	public void setSimulateur(Simulateur simulateur) {
		this.simulateur = simulateur;
	}

	public ArrayList<Case> getIncendies() {
		return incendies;
	}

	public void setIncendies(ArrayList<Case> incendies) {
		this.incendies = incendies;
	}
	
	
	
	
}
