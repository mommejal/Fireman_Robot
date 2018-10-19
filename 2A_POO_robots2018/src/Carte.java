import java.util.ArrayList;

public class Carte {
	private int tailleCases=100;
	private int nbLignes;
	private int nbColonnes;
	private Case[] map;
	
	public Carte(int nbL, int nbC) {
		this.nbLignes = nbL;
		this.nbColonnes = nbC;
		this.map = Case[nbL*nbC];
	}


	public int getNbLignes() {
		return nbLignes;
	}
	
	public int getNbColonnes() {
		return nbColonnes;
	}
	
	public Case getCase(int ligne, int colonne) {
		return this.map.get(ligne*this.nbColonnes+colonne);
	}
	
	public boolean voisinExiste(Case src, Direction dir) {
		
	}
	
	public Case getVoisin(Case src, Direction dir) {
		
	}
}