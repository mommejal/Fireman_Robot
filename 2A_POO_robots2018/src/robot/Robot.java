package robot;
import carte.Case;

public abstract class Robot {
	Case voisins[];
	protected Case position;
	protected double vitesse=0;
	protected int volume=0;

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
			this.setPosition(position.getVoisin(dir));
		}
	}

	@Override
	public String toString() {
		return "Robot [position=" + position + ", vitesse=" + vitesse + ", volume=" + volume + "]\n";
	}
	
	
}
