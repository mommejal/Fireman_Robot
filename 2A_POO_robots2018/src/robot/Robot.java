package robot;
import carte.Carte;
import carte.Case;
import carte.Direction;
import carte.NatureTerrain;

public abstract class Robot {
	/**
	 * La classe abstraite Robot regroupe les méthodes <b>générales</b> à tous les robots
	 * Cela permet de factoriser grandement le code notamment les methodes de mouvement des robots
	 */
	protected Carte carte;
	protected Case position;
	protected double vitesse=0;
	protected int reservoirmax;
	protected int volume = reservoirmax;
	protected double debit;
	
	
	
	public Robot(Carte carte) {
		super();
		this.carte = carte;
	}

	/**
	 * Deverse au max le volume entré en parametre mais peut verser moins si l'incendie est moins élévé que prévu
	 * Utilise uniquement le volume d'eau requis
	 */
	public void deverserEau(int vol) {
		int i = position.getLigne();
		int j = position.getColonne();

		int extinction;
		if (vol > position.getIncendie()) {
			//Au cas ou l'incendie est moins pire que prévu voire éteint
			extinction = position.getIncendie();
		}
		else {
			extinction = vol;
		}
		if (extinction >= volume) {
			carte.getCase(i, j).setIncendie(position.getIncendie()-volume);
			this.setVolume(0);
		}
		else {
			carte.getCase(i, j).setIncendie(carte.getCase(i, j).getIncendie()-extinction);
			this.setVolume(volume-extinction);
		}
	}
	
	abstract public void remplirReservoir();
	
	abstract public long dureeRemplirReservoir();

	public Case getPosition() {
		return position;
	}


	public abstract double getVitesse(NatureTerrain nature);

	
	public double getVitesse() {
		return vitesse;
	}
	
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
	
	
	
	public double getDebit() {
		return debit;
	}

	public void setDebit(double debit) {
		this.debit = debit;
	}

	public boolean voisinExiste(Direction dir) {
		return (position.getVoisins().containsKey(dir));
	}
	
	public abstract boolean canMove(Direction dir);
	
	public abstract void modifVitesse(Direction dir);
	
	/**
	 * Le robot vérifie si la case existe et si oui s'y déplace
	 * @param dir est la direction vers laquelle veut se deplacer le robot
	 */
	public void move(Direction dir) {
//		System.out.println("case avant :"+position);
		System.out.println(this.canMove(dir));
		System.out.println(voisinExiste(dir));
		if (this.canMove(dir) && voisinExiste(dir)) {
			System.out.println("là");
			this.modifVitesse(dir);
			this.setPosition(position.getVoisin(dir));
		}
		else {
			throw new IllegalArgumentException("La case doit exister et etre accessible au robot");
		}
//		System.out.println("case apres :"+position+dir);
	}

	@Override
	public String toString() {
		return "Robot [position=" + position + ", vitesse=" + vitesse + ", volume=" + volume + "]\n";
	}

	public Carte getCarte() {
		return carte;
	}

	public void setCarte(Carte carte) {
		this.carte = carte;
	}
	
	
}
