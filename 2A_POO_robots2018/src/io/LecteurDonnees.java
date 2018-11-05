package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.zip.DataFormatException;

import carte.Carte;
import carte.Case;
import robot.Robot;
import robot.RobotAChenille;
import robot.RobotARoue;

/**
 * Lecteur de cartes au format spectifié dans le sujet. Les données sur les
 * cases, robots puis incendies sont lues dans le fichier, puis simplement
 * affichées. A noter: pas de vérification sémantique sur les valeurs numériques
 * lues.
 *
 * IMPORTANT:
 *
 * Cette classe ne fait que LIRE les infos et les afficher. A vous de modifier
 * ou d'ajouter des méthodes, inspirées de celles présentes (ou non), qui CREENT
 * les objets au moment adéquat pour construire une instance de la classe
 * DonneesSimulation à partir d'un fichier.
 *
 * Vous pouvez par exemple ajouter une méthode qui crée et retourne un objet
 * contenant toutes les données lues: public static DonneesSimulation
 * creeDonnees(String fichierDonnees); Et faire des méthode creeCase(),
 * creeRobot(), ... qui lisent les données, créent les objets adéquats et les
 * ajoutentstatic  ds l'instance de DonneesSimulation.
 */
public class LecteurDonnees {
	static Carte carte;

	/**
	 * Lit et affiche le contenu d'un fichier de donnees (cases, robots et
	 * incendies). Ceci est méthode de classe; utilisation:
	 * LecteurDonnees.lire(fichierDonnees)
	 * 
	 * @param fichierDonnees
	 *            nom du fichier à lire
	 */
	public static void lire(String fichierDonnees) throws FileNotFoundException, DataFormatException {
		System.out.println("\n == Lecture du fichier" + fichierDonnees);
		LecteurDonnees lecteur = new LecteurDonnees(fichierDonnees);
		lecteur.storeCarte();
		lecteur.lireIncendies();
		lecteur.lireRobots();
		scanner.close();
		System.out.println("\n == Lecture terminee");
		System.out.println(carte.toString());
	}

	// Tout le reste de la classe est prive!

	private static Scanner scanner;

	/**
	 * Constructeur prive; impossible d'instancier la classe depuis l'exterieur
	 * 
	 * @param fichierDonnees
	 *            nom du fichier a lire
	 */
	private LecteurDonnees(String fichierDonnees) throws FileNotFoundException {
		scanner = new Scanner(new File(fichierDonnees));
		scanner.useLocale(Locale.US);
	}

	/**
	 * Lit et affiche les donnees de la carte.
	 * 
	 * @throws ExceptionFormatDonnees
	 */
	private void lireCarte() throws DataFormatException {
		ignorerCommentaires();
		try {
			int nbLignes = scanner.nextInt();
			int nbColonnes = scanner.nextInt();
			int tailleCases = scanner.nextInt(); // en m
			System.out.println("Carte " + nbLignes + "x" + nbColonnes + "; taille des cases = " + tailleCases);

			for (int lig = 0; lig < nbLignes; lig++) {
				for (int col = 0; col < nbColonnes; col++) {
					lireCase(lig, col);
				}
			}

		} catch (NoSuchElementException e) {
			throw new DataFormatException("Format invalide. " + "Attendu: nbLignes nbColonnes tailleCases");
		}
		// une ExceptionFormat levee depuis lireCase est remontee telle quelle
	}

	private void storeCarte() throws DataFormatException {
		ignorerCommentaires();
		try {
			int nbLignes = scanner.nextInt();
			int nbColonnes = scanner.nextInt();
			int tailleCases = scanner.nextInt();
			this.setCarte(new Carte(nbLignes, nbColonnes));
			carte.setTailleCases(tailleCases);
			// en m
			for (int lig = 0; lig < nbLignes; lig++) {
				for (int col = 0; col < nbColonnes; col++) {
					storeCase(lig, col, carte);
				}
			}

		} catch (NoSuchElementException e) {
			throw new DataFormatException("Format invalide. " + "Attendu: nbLignes nbColonnes tailleCases");
		}
		// une ExceptionFormat levee depuis lireCase est remontee telle quelle
	}

	/**
	 * Lit et affiche les donnees d'une case.
	 */
	private void lireCase(int lig, int col) throws DataFormatException {
		ignorerCommentaires();
		System.out.print("Case (" + lig + "," + col + "): ");
		String chaineNature = new String();
		// NatureTerrain nature;

		try {
			chaineNature = scanner.next();
			// si NatureTerrain est un Enum, vous pouvez recuperer la valeur
			// de l'enum a partir d'une String avec:
			// NatureTerrain nature = NatureTerrain.valueOf(chaineNature);

			verifieLigneTerminee();

			System.out.print("nature = " + chaineNature);

		} catch (NoSuchElementException e) {
			throw new DataFormatException("format de case invalide. " + "Attendu: nature altitude [valeur_specifique]");
		}

		System.out.println();
	}

	private void storeCase(int lig, int col, Carte carte) throws DataFormatException {
		ignorerCommentaires();
		String chaineNature = new String();
		// NatureTerrain nature;
		Case[] map = carte.getMap();
		int nbL = carte.getNbLignes();
		try {
			chaineNature = scanner.next();
			// si NatureTerrain est un Enum, vous pouvez recuperer la valeur
			// de l'enum a partir d'une String avec:
			// NatureTerrain nature = NatureTerrain.valueOf(chaineNature);

			verifieLigneTerminee();
			map[lig * nbL + col].setNature(chaineNature);
			

		} catch (NoSuchElementException e) {
			throw new DataFormatException("format de case invalide. " + "Attendu: nature altitude [valeur_specifique]");
		}

		System.out.println();
	}

	/**
	 * Lit et affiche les donnees des incendies.
	 */
	private void lireIncendies() throws DataFormatException {
		ignorerCommentaires();
		try {
			int nbIncendies = scanner.nextInt();
//			System.out.println("Nb d'incendies = " + nbIncendies);
			carte.setNbIncendies(nbIncendies);
			for (int i = 0; i < nbIncendies; i++) {
				lireIncendie(i);
			}

		} catch (NoSuchElementException e) {
			throw new DataFormatException("Format invalide. " + "Attendu: nbIncendies");
		}
	}

	/**
	 * Lit et affiche les donnees du i-eme incendie.
	 * 
	 * @param i
	 */
	private void lireIncendie(int i) throws DataFormatException {
		ignorerCommentaires();
//		System.out.print("Incendie " + i + ": ");

		try {
			int lig = scanner.nextInt();
			int col = scanner.nextInt();
			int intensite = scanner.nextInt();
			if (intensite <= 0) {
				throw new DataFormatException("incendie " + i + "nb litres pour eteindre doit etre > 0");
			}
			verifieLigneTerminee();
			carte.getMap()[lig*carte.getNbColonnes()+col].setIncendie(intensite); 

//			System.out.println("position = (" + lig + "," + col + ");\t intensite = " + intensite);

		} catch (NoSuchElementException e) {
			throw new DataFormatException("format d'incendie invalide. " + "Attendu: ligne colonne intensite");
		}
	}

	/**
	 * Lit et affiche les donnees des robots.
	 */
	private void lireRobots() throws DataFormatException {
		ignorerCommentaires();
		try {
			int nbRobots = scanner.nextInt();
//			System.out.println("Nb de robots = " + nbRobots);
			carte.setNbRobots(nbRobots);
			for (int i = 0; i < nbRobots; i++) {
				lireRobot(i);
			}

		} catch (NoSuchElementException e) {
			throw new DataFormatException("Format invalide. " + "Attendu: nbRobots");
		}
	}

	/**
	 * Lit et affiche les donnees du i-eme robot.
	 * 
	 * @param i
	 */
	private void lireRobot(int i) throws DataFormatException {
		ignorerCommentaires();
//		System.out.print("Robot " + i + ": ");
		

		try {
			int lig = scanner.nextInt();
			int col = scanner.nextInt();
//			System.out.print("position = (" + lig + "," + col + ");");
			String type = scanner.next();
			Robot robot;
			
			switch (type) {
			case "DRONE": //TODO
				robot = new RobotARoue();
				carte.getRobots().add(robot);
				break;
			case "ROUES":
				robot = new RobotARoue();
				carte.getRobots().add(robot);
				break;
			case "PATTES":
//				TODO
				robot = new RobotARoue();
				carte.getRobots().add(robot);
				break;
			case "CHENILLES":
				robot = new RobotAChenille();
				carte.getRobots().add(robot);
				break;
			default:
				robot=null;
				break;
			}
			robot.setPosition(carte.getMap()[lig*carte.getNbColonnes()+col]);

//			System.out.print("\t type = " + type);

			// lecture eventuelle d'une vitesse du robot (entier)
//			System.out.print("; \t vitesse = ");
			String s = scanner.findInLine("(\\d+)"); // 1 or more digit(s) ?
			// pour lire un flottant: ("(\\d+(\\.\\d+)?)");

			if (s == null) {
				System.out.print("valeur par defaut");
			} else {
				int vitesse = Integer.parseInt(s);
				System.out.print(vitesse);
				robot.setVitesse(vitesse);
			}
			verifieLigneTerminee();

			System.out.println();

		} catch (NoSuchElementException e) {
			throw new DataFormatException(
					"format de robot invalide. " + "Attendu: ligne colonne type [valeur_specifique]");
		}
	}

	/** Ignore toute (fin de) ligne commencant par '#' */
	private void ignorerCommentaires() {
		while (scanner.hasNext("#.*")) {
			scanner.nextLine();
		}
	}

	/**
	 * Verifie qu'il n'y a plus rien a lire sur cette ligne (int ou float).
	 * 
	 * @throws ExceptionFormatDonnees
	 */
	private void verifieLigneTerminee() throws DataFormatException {
		if (scanner.findInLine("(\\d+)") != null) {
			throw new DataFormatException("format invalide, donnees en trop.");
		}
	}

	static public Carte getCarte() {
		return carte;
	}

	public void setCarte(Carte carte) {
		this.carte = carte;
	}
	
	
}
