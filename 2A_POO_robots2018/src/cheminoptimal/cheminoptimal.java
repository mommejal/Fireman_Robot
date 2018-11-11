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

	// initialisation des éléments utiles à l'algorithme

	private int coordCaseDepart[] = new int[2];
	//nblignes, nbcolonnes
	private int n;
	private int m;
	private long[] tabRechercheTemps;

	private Direction directions[] = { Direction.NORD, Direction.SUD, Direction.EST, Direction.OUEST };
	private Direction tabRecherchePrecedent[];

	public cheminoptimal(Robot robot) {
		super();
		this.robot = robot;
		System.out.println(robot);
		System.out.println(robot.getPosition().getLigne());

		this.coordCaseDepart[0] = robot.getPosition().getLigne();
		this.coordCaseDepart[1] = robot.getPosition().getColonne();
		this.n = robot.getCarte().getNbLignes();
		this.m = robot.getCarte().getNbColonnes();
		this.tabRechercheTemps = new long[n*m];
		this.tabRecherchePrecedent = new Direction[n*m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.println(i+","+j);
				this.tabRechercheTemps[i*m+j] = -1;
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				this.tabRecherchePrecedent[i*m+j] = Direction.SUD;
			}
		}
		System.out.println(tabRechercheTemps);
		this.tabRechercheTemps[coordCaseDepart[0]*m+coordCaseDepart[1]] = 0;
		// this.aExplorer = aExplorer;
		// this.directions = directions;
	}

	private Stack<int[]> aExplorer = new Stack<int[]>();
	{
		aExplorer.push(coordCaseDepart);
	}

	// calcul des distances et des chemins optimaux avec l'algorithme de Dijkstra

	private void calcul() {
		while (!aExplorer.empty()) {
			Stack<int[]> newAExplorer = new Stack<int[]>();
			while (!aExplorer.empty()) {
				for (int i = 0; i < n; i++) {
					for (int j = 0; j < m; j++) {
						System.out.print(tabRecherchePrecedent[i*m+j]+"    ");
					}
					System.out.println();
				}
				int[] current = aExplorer.pop();
				System.out.println(robot.getCarte().getCase(current[0], current[1]));
				double vitesse = robot.getVitesse(robot.getCarte().getCase(current[0], current[1]).getNature());
				for (Direction dir : directions) {
					System.out.println(robot.getCarte().getCase(current[0], current[1]).getVoisins().containsKey(dir) && robot.canMoveFrom(robot.getCarte().getCase(current[0], current[1]), dir));
					if (robot.getCarte().getCase(current[0], current[1]).getVoisins().containsKey(dir) && robot.canMoveFrom(robot.getCarte().getCase(current[0], current[1]), dir)) {
						Case voisin = robot.getCarte().getCase(current[0], current[1]).getVoisin(dir);
//						System.out.println(voisin);
						int coord[] = { voisin.getLigne(), voisin.getColonne() };
						if (tabRechercheTemps[coord[0]*m+coord[1]] == -1) {
							tabRechercheTemps[coord[0]*m+coord[1]] = (long) (3600 / vitesse
									+ tabRechercheTemps[current[0]*m+current[1]]);
							tabRecherchePrecedent[coord[0]*m+coord[1]] = dir;
							newAExplorer.push(coord);
						} else {
							if (tabRechercheTemps[coord[0]*m+coord[1]] > (long) (3600 / vitesse
									+ tabRechercheTemps[current[0]*m+current[1]])) {
								tabRechercheTemps[coord[0]*m+coord[1]] = (long) (3600 / vitesse
										+ tabRechercheTemps[current[0]*m+current[1]]);
								tabRecherchePrecedent[coord[0]*m+coord[1]] = dir;
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
		if (!aExplorer.empty()) { // teste si l'algorithme de Dijkstra a déjà été appliqué auparavant dans
									// cette situation
			this.calcul();
		}
		return tabRechercheTemps[caseVisee.getLigne()*m+caseVisee.getColonne()];
	}

	public void travelTo(Case caseVisee) {
		System.out.println("travelto");
		if (!aExplorer.empty()) {
			this.calcul();
		}
		Case current = caseVisee;
		Stack<Direction> chemin = new Stack<Direction>();
		while (current.getLigne() != robot.getPosition().getLigne()
				|| current.getColonne() != robot.getPosition().getColonne()) {
			chemin.push(tabRecherchePrecedent[current.getLigne()*m+current.getColonne()]);
			switch (chemin.peek()) {
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
		System.out.println(chemin);
		while (!chemin.empty()) {
			robot.getSimulateur().ajouteEvenement(new Deplacement(chemin.pop(), robot));
		}
		System.out.println(robot.getSimulateur().getEvents());
	}

}