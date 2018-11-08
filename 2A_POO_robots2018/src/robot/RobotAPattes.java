package robot;

import carte.Case;
import carte.Direction;
import carte.NatureTerrain;

public class RobotAPattes extends Robot {
	
	double debit = 10;
	double vitesse = 30;

	@Override
	public void remplirReservoir() {
		// Le Robot à Pattes n'a jamais besoin de se remplir
		
	}
	
	

	@Override
	public long dureeRemplirReservoir() {
		return 0;
	}



	@Override
	public double getVitesse(NatureTerrain nature) {
		if (nature == NatureTerrain.ROCHE) {
			return 10;
		}
		else {
			return 30;
		}
	}

	@Override
	public boolean canMove(Direction dir) {
		// Cette fonction vérifie que le robot puisse aller là  ou il veut au niveau de la nature du terrain
		Case dest = position.getVoisin(dir);
		NatureTerrain natureDest = dest.getNature();
		switch (natureDest) {
		case EAU:
			return false;
		default:
			return true;
		}
	}

	@Override
	public void modifVitesse(Direction dir) {
		Case dest = position.getVoisin(dir);
		NatureTerrain natureDest = dest.getNature();
		NatureTerrain naturePos = position.getNature();
		if ((naturePos == NatureTerrain.ROCHE)&&(natureDest!=NatureTerrain.ROCHE)){
			this.setVitesse(30);
		}
		if ((naturePos != NatureTerrain.ROCHE)&&(natureDest==NatureTerrain.ROCHE)){
			this.setVitesse(30);
		}
		
	}
	
	
}
