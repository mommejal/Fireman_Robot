package graphic_interface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;
import gui.Text;


public class TestGUI {
    public static void main(String[] args) {
        // crée la fenêtre graphique dans laquelle dessiner
    	
    	Carte carte = new Carte();
        GUISimulator gui = new GUISimulator(800, 800, Color.BLACK);
    }
}