package test;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

import carte.Carte;
import carte.Direction;
import graphicinterface.GUI;
import gui.GUISimulator;
import io.LecteurDonnees;
import simulateur.Deplacement;
import simulateur.Evenement;
import simulateur.Simulateur;

public class TestSimDiscrete {

	public static void main(String[] args) {
        // crée la fenêtre graphique dans laquelle dessiner
    	if (args.length < 1) {
            System.out.println("Syntaxe: java TestLecteurDonnees <nomDeFichier>");
            System.exit(1);
        }

        try {
            LecteurDonnees.lire(args[0]);
        } catch (FileNotFoundException e) {
            System.out.println("fichier " + args[0] + " inconnu ou illisible");
        } catch (DataFormatException e) {
            System.out.println("\n\t**format du fichier " + args[0] + " invalide: " + e.getMessage());
        }
        Carte carte = LecteurDonnees.getCarte();
        carte.setTailleCases(50);
        GUISimulator gui = new GUISimulator(800, 800, Color.BLACK);
        GUI gui_carte = new GUI(carte, gui);
//        Simulateur simulateur = gui_carte.getSimulateur();
        Simulateur simulateur = new Simulateur();
        
        // Ajout d'évenements
        simulateur.ajouteEvenement(new Deplacement(Direction.EST,carte.getRobots().get(0)));
        simulateur.ajouteEvenement(new Deplacement(Direction.SUD,carte.getRobots().get(0)));
        simulateur.ajouteEvenement(new Deplacement(Direction.SUD,carte.getRobots().get(0)));
        simulateur.ajouteEvenement(new Deplacement(Direction.NORD,carte.getRobots().get(0)));
        simulateur.ajouteEvenement(new Deplacement(Direction.NORD,carte.getRobots().get(0)));
        simulateur.ajouteEvenement(new Deplacement(Direction.NORD,carte.getRobots().get(0)));
        simulateur.ajouteEvenement(new Deplacement(Direction.NORD,carte.getRobots().get(0)));
        simulateur.ajouteEvenement(new Deplacement(Direction.NORD,carte.getRobots().get(0)));
        simulateur.ajouteEvenement(new Deplacement(Direction.NORD,carte.getRobots().get(1)));
        simulateur.ajouteEvenement(new Deplacement(Direction.OUEST,carte.getRobots().get(1)));
        simulateur.ajouteEvenement(new Deplacement(Direction.NORD,carte.getRobots().get(1)));
        simulateur.ajouteEvenement(new Deplacement(Direction.NORD,carte.getRobots().get(1)));
        simulateur.ajouteEvenement(new Deplacement(Direction.NORD,carte.getRobots().get(2)));
        simulateur.ajouteEvenement(new Deplacement(Direction.NORD,carte.getRobots().get(2)));
        simulateur.getEvents().getLast().toString();
        gui_carte.setSimulateur(simulateur);
        gui_carte.getSimulateur().getEvents().toString();
//        System.out.println(new Deplacement(Direction.NORD,carte.getRobots().get(0)).toString());
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAaa");
        
        
        
	}

}