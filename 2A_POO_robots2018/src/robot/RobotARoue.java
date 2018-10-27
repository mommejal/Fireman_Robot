package robot;

import carte.NatureTerrain;

public class RobotARoue extends Robot {
	int reservoirmax = 5000;
	int debit = 100/5;
	double vitesse = 80;
	@Override
	public void deverserEau(int vol) {
		//100 litres en 5 sec
		if (volume>debit) {
			volume -= debit;
		}
	}

	@Override
	public void remplirReservoir() {
//		long datefin = a.getDate()+30*60; //Temps pour se remplir
//		boolean sortie = false;
//		while (!sortie) {
//			// Dangereux ? trop de calcul ?
//			sortie = a.getDate()<datefin);
//		}
//		this.setVolume(reservoirmax);
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

	public int getDebit() {
		return debit;
	}

	public void setDebit(int debit) {
		this.debit = debit;
	}
	
	
}
