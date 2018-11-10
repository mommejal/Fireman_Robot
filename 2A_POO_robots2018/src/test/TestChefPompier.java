package test;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

import carte.Carte;
import graphicinterface.GUI;
import gui.GUISimulator;
import io.LecteurDonnees;
import simulateur.ChefPompier;
import simulateur.Simulateur;

public class TestChefPompier {

	public static void main(String[] args) {
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
		Simulateur simulateur = new Simulateur();
		ChefPompier chef = new ChefPompier();
		chef.setCarte(carte);
		chef.setSimulateur(simulateur);
		long pas = 10; // PAs de simulation
		while (!simulateur.simulationTerminee()) {
			chef.faireLeChef(pas);
		}

	}

}
