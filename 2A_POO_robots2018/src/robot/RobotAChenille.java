package robot;

import carte.Case;
import carte.Direction;
import carte.NatureTerrain;

public class RobotAChenille extends Robot {

	int reservoirmax = 2000;
	int debit = 100 / 5;
	double vitesse = 60;

	@Override
	public void deverserEau(int vol) {
		if (volume > debit) {
			volume -= debit;
		}

	}

	@Override
	public void remplirReservoir() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setVitesse(double vitesse) {
		// TODO Auto-generated method stub
		super.setVitesse(vitesse);
	}

	@Override
	public double getVitesse(NatureTerrain nature) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
	@Override
	public void modifVitesse(Direction dir) {
		NatureTerrain natureDep = position.getNature();
		if (natureDep==NatureTerrain.FORET) {
			vitesse *=2;
		}
		Case dest = position.getVoisin(position, dir);
		NatureTerrain natureDest = dest.getNature();
		if (natureDest==NatureTerrain.FORET) {
			vitesse /=2;
		}
	}

	@Override
	public boolean canMove(Direction dir) {
		// Cette fonction vérifie que le robot puisse aller là  ou il veut
		Case dest = position.getVoisin(position, dir);
		NatureTerrain natureDest = dest.getNature();
		switch (natureDest) {
		case EAU:
			return false;
		case ROCHE:
			return false;
		case FORET:
			return true;
		default:
			return true;
		}

	}

}
