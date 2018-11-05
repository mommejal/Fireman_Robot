package carte;

import java.util.ArrayList;
import java.util.Arrays;

import robot.Robot;

public class Carte {
	private int tailleCases;
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
			}
		}
		for (Case cur : map) {
			int i = cur.getLigne();
			int j = cur.getColonne();
			for (Direction dir : Direction.values()) {
				if (cur.voisinExiste(dir)) {
					if (dir == Direction.NORD) {
						cur.getVoisins().put(dir,this.getCase(i-1, j));
					}

					else if (dir == Direction.SUD) {
						cur.getVoisins().put(dir,this.getCase(i+1, j));
					}

					else if (dir == Direction.EST) {
						cur.getVoisins().put(dir,this.getCase(i, j+1));
					}

					else {
						cur.getVoisins().put(dir,this.getCase(i, j-1));
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

	@Override
	public String toString() {
		return "Carte [map=" + Arrays.toString(map) + "]\n\n" + "nbrobots" + nbRobots + "\n liste robots: \n"
				+ robots.toString();
	}

}