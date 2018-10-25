package robot;

import carte.Case;
import carte.Direction;
import carte.NatureTerrain;

public abstract class Robot {
	Case position;
	double vitesse;
	int volume;

	public abstract void deverserEau(int vol);

	abstract public void remplirReservoir();

	public Case getPosition() {
		return position;
	}

	public abstract double getVitesse(NatureTerrain nature);

	public void setVitesse(double vitesse) {
		this.vitesse = vitesse;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public void setPosition(Case position) {
		this.position = position;
	}
	
	public abstract boolean canMove(Direction dir);
	
	public abstract void modifVitesse(Direction dir);
	
	public void move(Direction dir) {
		if (this.canMove(dir)) {
			this.modifVitesse(dir);
			this.setPosition(position.getVoisin(position, dir));
		}
	}

}
