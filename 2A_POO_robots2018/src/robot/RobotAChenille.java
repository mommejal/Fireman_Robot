package robot;
import carte.Carte;
import carte.Case;
import carte.Direction;
import carte.NatureTerrain;

public class RobotAChenille extends Robot {
	
	int reservoirmax = 2000;
	double debit = 100/5;
	double vitesse = 60;
	
	
	public RobotAChenille(Carte carte) {
		super(carte);
		// TODO Auto-generated constructor stub
	}

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
	public void remplirReservoir() {
	    for(Case voisin : position.getVoisins().values()){
	    	if (voisin.getNature() == NatureTerrain.EAU) {
	    		//TODO G�rer le temps
	    		volume = reservoirmax;
	      }
		
	}
	}
	
	

	@Override
	public long dureeRemplirReservoir() {
		return 5*60;
	}

	@Override
	public void setVitesse(double vitesse) {
		if (vitesse > 80) {
			vitesse = 80;
		}
		super.setVitesse(vitesse);
	}

	@Override
	public boolean canMove(Direction dir) {
		// Cette fonction vérifie que le robot puisse aller là  ou il veut
		Case dest = position.getVoisin(dir);
		NatureTerrain natureDest = dest.getNature();
		switch (natureDest) {
		case ROCHE:
			return false;
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
		if ((naturePos == NatureTerrain.FORET)&&(natureDest!=NatureTerrain.FORET)){
			this.setVitesse(vitesse*2);
		}
		if ((naturePos != NatureTerrain.FORET)&&(natureDest==NatureTerrain.FORET)){
			this.setVitesse(vitesse/2);
		}
	}
	
}
