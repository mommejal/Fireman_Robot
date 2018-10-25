package graphic_interface;
import java.awt.Color;

import carte.Carte;
import gui.GUISimulator;


public class TestGUI {
    public static void main(String[] args) {
        // crée la fenêtre graphique dans laquelle dessiner
    	
    	Carte carte = new Carte();
        GUISimulator gui = new GUISimulator(800, 800, Color.BLACK);
    }
}