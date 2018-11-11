package test;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

import carte.Carte;
import carte.Direction;
import cheminoptimal.cheminoptimal;
import graphicinterface.GUI;
import gui.GUISimulator;
import io.LecteurDonnees;
import simulateur.Deplacement;
import simulateur.SimulateurBis;

public class TestMouvementDjikstra {

	public static void main(String[] args) {
        /**
         * Ce test a pour but de tester si chaque Robot se déplace correctement en utilisant l'algo de Dijkstra
         */
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
        GUI gui_carte = new GUI(carte, gui, args[0]);
        SimulateurBis simulateur = carte.getRobots().get(0).getSimulateur();
        gui_carte.setSimulateur(simulateur);
        gui_carte.setSimulateur(simulateur);
        carte.getRobots().get(0).toString();
//        carte.getRobots().get(0).getSimulateur().ajouteEvenement(new Deplacement(Direction.NORD,carte.getRobots().get(1)));
        System.out.println("ON VA AFFICHER LE ROBOT 0");
//        carte.getRobots().get(0).getSimulateur().toString();
//        carte.getRobots().get(0).getPosition().toString();
        System.out.println(carte.getRobots());
        cheminoptimal co0 = new cheminoptimal(carte.getRobots().get(0));
        cheminoptimal co1 = new cheminoptimal(carte.getRobots().get(1));
        cheminoptimal co2 = new cheminoptimal(carte.getRobots().get(2));
        co0.travelTo(carte.getCase(6, 6));
        co1.travelTo(carte.getCase(5, 5));
        co2.travelTo(carte.getCase(4, 4));
//        System.out.println(simulateur.getEvents());
//        System.out.println(co.getShortestTime(carte.getCase(6, 6)));
//        new CheminOptimal(carte.getRobots().get(1)).travelTo(carte.getCase(2, 3));
//        new CheminOptimal(carte.getRobots().get(2)).travelTo(carte.getCase(1, 1));
        System.out.println("Le robot devrait etre en 6,6");
        carte.getRobots().get(0).toString();
        System.out.println("Le robot devrait etre en 2,3");
        carte.getRobots().get(1).toString();
        System.out.println("Le robot devrait etre en 1,1");
        carte.getRobots().get(2).toString();
        
        
        
//        Simulateur simulateur = new Simulateur();     
        // Ajout d'Ã©venements

//        gui_carte.getSimulateur().getEvents().toString();

	}

}
