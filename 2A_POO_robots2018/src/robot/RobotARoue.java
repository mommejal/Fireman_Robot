package robot;

import carte.Case;
import carte.Direction;
import carte.NatureTerrain;

public class RobotARoue extends Robot {
	int reservoirmax = 5000;
	double debit = 100/5;
	double vitesse = 80;

	@Override
	public void remplirReservoir() {
//		long datefin = a.getDate()+30*60; //Temps pour se remplir
//		boolean sortie = false;
//		while (!sortie) {
//			// Dangereux ? trop de calcul ?
//			sortie = a.getDate()<datefin);
//		}
		this.setVolume(reservoirmax);
	}
	
	
	

	@Override
	public long dureeRemplirReservoir() {
		return 600;		
	}




	@Override
	public boolean canMove(Direction dir) {
		// Cette fonction vérifie que le robot puisse aller là  ou il veut
		Case dest = position.getVoisin(dir);
		NatureTerrain natureDest = dest.getNature();
		switch (natureDest) {
		case HABITAT:
			return true;
		case TERRAIN_LIBRE:
			return true;
		default:
			return false;
		}
	}

	@Override
	public void modifVitesse(Direction dir) {
		//Rien en particulier pour le robot à roues
	}

	@Override
	public double getVitesse(NatureTerrain nature) {
		return vitesse;
	}

	public int getReservoirmax() {
		return reservoirmax;
	}

	public void setReservoirmax(int reservoirmax) {
		this.reservoirmax = reservoirmax;
	}

	public double getDebit() {
		return debit;
	}

	public void setDebit(int debit) {
		this.debit = debit;
	}
	
	
}
