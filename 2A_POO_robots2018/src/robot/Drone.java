package robot;

import carte.Carte;
import carte.Case;
import carte.Direction;
import carte.NatureTerrain;

public class Drone extends Robot {
	double vitesse = 100;
	double debit = 10000 / 30;
	int reservoirmax = 10000;

	public Drone(Carte carte) {
		super(carte);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void deverserEau(int vol) {
		volume = 0;
		int extinction;
		if (position.getIncendie() == 0) {
			// On ne fait rien
		} else {
			extinction = vol;
			if (extinction >= volume) {
				position.setIncendie(position.getIncendie() - volume);
				this.setVolume(0);
			} else {
				position.setIncendie(position.getIncendie() - extinction);
				this.setVolume(volume - extinction);
			}
		}

	}

	@Override
	public void goRemplir() {
		// TODO Auto-generated method stub

	}

	@Override
	public long dureeRemplirReservoir() {
		return 30 * 60;
	}

	@Override
	public void remplirReservoir() {
		if (this.getPosition().getNature() == NatureTerrain.EAU) {
			volume = reservoirmax;
		}
		this.incrementeDateWhereFree(dureeRemplirReservoir());
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

	@Override
	public boolean canMove(Direction dir) {
		// Le drone peut se deplacer sur tous les terrains
		return true;
	}
	
	@Override
	public boolean canMoveFrom(Case depart, Direction dir) {
		// Le drone peut se deplacer sur tous les terrains
		return true;
	}

	@Override
	public void modifVitesse(Direction dir) {
		// Ne fait rien, la vitesse du drone ne dépend pas du terrain

	}

	public int getReservoirmax() {
		return reservoirmax;
	}

}
