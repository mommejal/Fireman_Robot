package robot;

import carte.NatureTerrain;

public class Drone extends Robot {
	double vitesse = 100;
	int debit = 10000/30;
	int reservoirmax = 10000;
	
	@Override
	public void deverserEau(int vol) {
		//TODO Gérer le temps 30 sec pour se vider
		volume = 0;
		
	}

	@Override
	public void remplirReservoir() {
		if (this.getPosition().getNature()==NatureTerrain.EAU) {
			volume = reservoirmax;
		}	
		//TODO Gérer le temps 30 min de recharge	
	}

	@Override
	public double getVitesse(NatureTerrain nature) {
		return vitesse;
	}

	public void setVitesse(double vitesse) {
		if (vitesse > 150) {
			vitesse = 150;
		}
		this.vitesse = vitesse;
	}
	
	
}
