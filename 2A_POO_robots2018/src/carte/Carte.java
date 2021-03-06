package carte;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import robot.Robot;

public class Carte implements Cloneable{
	private int tailleCases;
	// en km
	private int tailleReelleCases = 1; 
	private int nbLignes;
	private int nbColonnes;
	private int nbIncendies = 0;
	private int nbRobots = 0;
	private ArrayList<Robot> robots;
	private Case[] map;

	public Carte(int nbL, int nbC) {
		this.nbLignes = nbL;
		this.nbColonnes = nbC;
		this.map = new Case[nbL * nbC];
		this.robots = new ArrayList<Robot>();
		for (int i = 0; i < nbL; i++) {
			for (int j = 0; j < nbC; j++) {
				this.map[i * nbL + j] = new Case();
				this.map[i * nbL + j].setLigne(i);
				this.map[i * nbL + j].setColonne(j);
				this.map[i * nbL + j].setNbLignes(nbL);
				this.map[i * nbL + j].setNbColonnes(nbC);
				this.map[i * nbL + j].setTailleCases(tailleCases);
				this.map[i * nbL + j].setVoisins(new HashMap<Direction, Case>());
			}
		}
		for (Case cur : map) {
			int i = cur.getLigne();
			int j = cur.getColonne();
			for (Direction dir : Direction.values()) {
				if (cur.voisinExiste(dir)) {
					if (dir == Direction.NORD) {
						cur.getVoisins().put(dir, this.getCase(i - 1, j));
					}

					else if (dir == Direction.SUD) {
						cur.getVoisins().put(dir, this.getCase(i + 1, j));
					}

					else if (dir == Direction.EST) {
						cur.getVoisins().put(dir, this.getCase(i, j + 1));
					}

					else {
						cur.getVoisins().put(dir, this.getCase(i, j - 1));
					}

				}
			}
		}
	}

	public int getNbLignes() {
		return nbLignes;
	}

	public int getNbColonnes() {
		return nbColonnes;
	}

	public Case[] getMap() {
		return map;
	}

	public Case getCase(int ligne, int colonne) {
		return this.map[ligne * this.nbColonnes + colonne];
	}

	public int getTailleCases() {
		return tailleCases;
	}

	public void setTailleCases(int tailleCases) {
		this.tailleCases = tailleCases;
		for (Case cur : map) {
			cur.setTailleCases(tailleCases);
		}
	}

	public void setNbLignes(int nbLignes) {
		this.nbLignes = nbLignes;
	}

	public void setNbColonnes(int nbColonnes) {
		this.nbColonnes = nbColonnes;
	}

	public void setMap(Case[] map) {
		this.map = map;
	}

	public int getNbIncendies() {
		return nbIncendies;
	}

	public void setNbIncendies(int nbIncendies) {
		this.nbIncendies = nbIncendies;
	}

	public int getNbRobots() {
		return nbRobots;
	}

	public void setNbRobots(int nbRobots) {
		this.nbRobots = nbRobots;
	}

	public ArrayList<Robot> getRobots() {
		return robots;
	}

	public void setRobots(ArrayList<Robot> robots) {
		this.robots = robots;
	}

	
	public int getTailleReelleCases() {
		return tailleReelleCases;
	}
	
	public Object clone() {
		Object o = null;
		try {
			// On r�cup�re l'instance � renvoyer par l'appel de la 
			// m�thode super.clone()
			o = super.clone();
		} catch(CloneNotSupportedException cnse) {
			// Ne devrait jamais arriver car nous impl�mentons 
			// l'interface Cloneable
			cnse.printStackTrace(System.err);
		}
		// on renvoie le clone
		return o;
	}

	@Override
	public String toString() {
		return "Carte [map=" + Arrays.toString(map) + "]\n\n" + "nbrobots" + nbRobots + "\n liste robots: \n"
				+ robots.toString();
	}

	

}