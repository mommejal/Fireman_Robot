package robot;

import carte.Carte;
import carte.Case;
import carte.Direction;
import carte.NatureTerrain;

public class RobotARoue extends Robot {
	int reservoirmax = 5000;
	double debit = 100 / 5;
	double vitesse = 80;
	int volume = 5000;

	public RobotARoue(Carte carte) {
		super(carte);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void remplirReservoir() {
		this.setVolume(reservoirmax);
		this.incrementeDateWhereFree(dureeRemplirReservoir());
	}

	@Override
	public long dureeRemplirReservoir() {
		return (long) 600;
	}

	@Override
	public boolean canMove(Direction dir) {
		// Cette fonction vérifie que le robot puisse aller là ou il veut
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
	public boolean canMoveFrom(Case depart, Direction dir) {
		// Cette fonction vérifie que le robot puisse aller là  ou il veut à partir d'une case donnée
		Case dest = depart.getVoisin(dir);
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
	public void goRemplir() {
		// TODO Auto-generated method stub
	}

	@Override
	public void modifVitesse(Direction dir) {
		// Rien en particulier pour le robot à roues
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
