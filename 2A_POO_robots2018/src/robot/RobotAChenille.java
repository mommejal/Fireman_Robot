package robot;

import carte.Case;
import carte.NatureTerrain;

public class RobotAChenille extends Robot {
	
	int reservoirmax = 2000;
	int debit = 100/5;
	double vitesse = 60;
	
	
	@Override
	public double getVitesse(NatureTerrain nature) {
		if (nature.equals(NatureTerrain.FORET)) {
			return vitesse/2;
		}
		else {
			return vitesse;
		}
	}

	@Override
	public void deverserEau(int vol) {
		// TODO Gérer le temps
		if (volume>debit) {
			volume -= debit;
		}
		
	}

	@Override
	public void remplirReservoir() {
	    for(Case voisin : voisins){
	    	if (voisin.getNature() == NatureTerrain.EAU) {
	    		//TODO Gérer le temps
	    		volume = reservoirmax;
	      }
		
	}
	}

	@Override
	public void setVitesse(double vitesse) {
		if (vitesse > 80) {
			vitesse = 80;
		}
		super.setVitesse(vitesse);
	}
	
}
