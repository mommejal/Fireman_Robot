package robot;

import carte.Carte;
import carte.Case;
import carte.Direction;
import carte.NatureTerrain;
import simulateur.Simulateur;

public abstract class Robot {
	/**
	 * La classe abstraite Robot regroupe les méthodes <b>générales</b> à tous
	 * les robots Cela permet de factoriser grandement le code notamment les
	 * methodes de mouvement des robots
	 */
	protected Carte carte;
	protected Case position;
	protected double vitesse = 0;
	protected int reservoirmax;
	protected int volume;
	protected double debit;
	protected long dateWhereFree = 0;
	protected Simulateur simulateur;
	protected int numeroRobot = 0;

	public Robot(Carte carte) {
		super();
		this.carte = carte;
	}

	/**
	 * Deverse au max le volume entré en parametre mais peut verser moins si
	 * l'incendie est moins élévé que prévu Utilise uniquement le volume d'eau
	 * requis
	 */
	public void deverserEau(int vol) {
		int i = position.getLigne();
		int j = position.getColonne();

		int extinction;
		if (vol > position.getIncendie()) {
			// Au cas ou l'incendie est moins pire que prévu voire éteint
			extinction = position.getIncendie();
		} else {
			extinction = vol;
		}
		if (extinction >= volume) {
			carte.getCase(i, j).setIncendie(position.getIncendie() - volume);
			this.setVolume(0);
		} else {
			carte.getCase(i, j).setIncendie(carte.getCase(i, j).getIncendie() - extinction);
			this.setVolume(volume - extinction);
		}
	}

	public boolean isFree(long date) {
		return (simulateur.getDateSimulation() < dateWhereFree);
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

	
	public int getNumeroRobot() {
		return numeroRobot;
	}

	public void setNumeroRobot(int numeroRobot) {
		this.numeroRobot = numeroRobot;
	}

	public boolean voisinExiste(Direction dir) {
		return (position.getVoisins().containsKey(dir));
	}

	public abstract void goRemplir();

	public abstract boolean canMove(Direction dir);

	public abstract void modifVitesse(Direction dir);

	public void move(Direction dir) {
		/**
		 * Le robot vérifie si la case existe et si oui s'y déplace
		 * 
		 * @param dir est la direction vers laquelle veut se deplacer le robot
		 */
		if (this.canMove(dir) && voisinExiste(dir)) {
			this.modifVitesse(dir);
			this.setPosition(position.getVoisin(dir));
		} else {
			throw new IllegalArgumentException("La case doit exister et etre accessible au robot");
		}
	}

	public void moveTo(Case dest) {
		// TODO
		// Je m'attends � ce que cette m�thode ajoute au simuatuer les �venements requis
		// au simulateur
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

	public int getReservoirmax() {
		return reservoirmax;
	}

	public long getDateWhereFree() {
		return dateWhereFree;
	}

	public void setDateWhereFree(long dateWhereFree) {
		this.dateWhereFree = dateWhereFree;
	}

	public Simulateur getSimulateur() {
		return simulateur;
	}

	public void setSimulateur(Simulateur simulateur) {
		this.simulateur = simulateur;
	}

	public void incrementeDateWhereFree(long date) {
		this.dateWhereFree += date;
	}

}
