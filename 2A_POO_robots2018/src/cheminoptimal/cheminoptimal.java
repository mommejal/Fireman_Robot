package cheminoptimal;
import java.util.Stack;

import carte.Case;
import carte.Direction;
import robot.Robot;
import simulateur.Deplacement;

public class cheminoptimal {


	/** attribut necessaires pour le calcul du plus court chemin **/
	
	private Robot robot;
	
	/** calcul du plus court chemin **/
	
	//initialisation des éléments utiles à l'algorithme
	
	private int coordCaseDepart[] = {robot.getPosition().getLigne(), robot.getPosition().getColonne()};
	private int n = robot.getCarte().getNbLignes();
	private int m = robot.getCarte().getNbColonnes();
	
	private long tabRechercheTemps[][];{
		for (int i=0; i<n; i++) {
			for (int j=0; j<m ; j++) {
				tabRechercheTemps[i][j] = -1;
			}
		}
	}
	
	private Direction tabRecherchePrecedent[][];{
		for (int i=0; i<n; i++) {
			for (int j=0; j<m; j++) {
				tabRecherchePrecedent[i][j]= Direction.SUD;
			}
		}
		tabRechercheTemps[coordCaseDepart[0]][coordCaseDepart[1]] = 0;
	}
	
	private Stack<int[]> aExplorer = new Stack<int[]>();{
		aExplorer.push(coordCaseDepart);
	}
	
	
	private Direction directions[] = {Direction.NORD, Direction.SUD, Direction.EST, Direction.OUEST};
	
	//calcul des distances et des chemins optimaux avec l'algorithme de Dijkstra
	
	private void calcul() {
		while (!aExplorer.empty()) {
			Stack<int[]> newAExplorer = new Stack<int[]>();
			while (!aExplorer.empty()) {
				int[] current = aExplorer.pop();
				double vitesse = robot.getVitesse(robot.getCarte().getCase(current[0], current[1]).getNature());
				for (Direction dir : directions) {
					if (robot.canMoveFrom(robot.getCarte().getCase(current[0], current[1]), dir)) {
						Case voisin = robot.getCarte().getCase(current[0], current[1]).getVoisin(dir);
						int coord[] = {voisin.getLigne(), voisin.getColonne()};
						if (tabRechercheTemps[coord[0]][coord[1]] == -1) {
							tabRechercheTemps[coord[0]][coord[1]] = (long)(3600/vitesse + tabRechercheTemps[current[0]][current[1]]);
							tabRecherchePrecedent[coord[0]][coord[1]] = dir;
							newAExplorer.push(coord);
						}
						else {
							if (tabRechercheTemps[coord[0]][coord[1]] > (long)(3600/vitesse + tabRechercheTemps[current[0]][current[1]])) {
								tabRechercheTemps[coord[0]][coord[1]] = (long)(3600/vitesse + tabRechercheTemps[current[0]][current[1]]);
								tabRecherchePrecedent[coord[0]][coord[1]] = dir;
								newAExplorer.push(coord);
							}
						}
					}
				}
			}
			aExplorer = newAExplorer;
		}
	}
	
	public long getShortestTime(Case caseVisee) {
		if (!aExplorer.empty()) {		//teste si l'algorithme de Dijkstra a déjà été appliqué auparavant dans cette situation
			this.calcul();
		}
		return tabRechercheTemps[caseVisee.getLigne()][caseVisee.getColonne()];
	}
	
	public void travelTo(Case caseVisee) {
		if (!aExplorer.empty()) {
			this.calcul();
		}
		Case current = caseVisee;
		Stack<Direction> chemin = new Stack<Direction>();
		while (current.getLigne() != robot.getPosition().getLigne() || current.getColonne() != robot.getPosition().getColonne()) {
			chemin.push(tabRecherchePrecedent[current.getLigne()][current.getColonne()]);
			switch(chemin.peek()) {
			case NORD:
				current = current.getVoisin(Direction.SUD);
				break;
			case SUD:
				current = current.getVoisin(Direction.NORD);
				break;
			case EST:
				current = current.getVoisin(Direction.OUEST);
				break;
			default:
				current = current.getVoisin(Direction.EST);
			}
		}
		while (!chemin.empty()) {
			robot.getSimulateur().ajouteEvenement(new Deplacement(chemin.pop(), robot));
		}
	}
		
}
