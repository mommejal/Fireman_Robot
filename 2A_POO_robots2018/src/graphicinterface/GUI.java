package graphicinterface;
import java.awt.Color;
import java.util.Deque;

import carte.Carte;
import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;
import gui.Text;
import io.LecteurDonnees;
import robot.Robot;
import simulateur.Evenement;
import simulateur.Simulateur;




public class GUI implements Simulable {
	private Carte carteInitiale;
	private GUISimulator gui;
	private Carte carte;
	public Simulateur simulateur;
	private long intervalle = 100;
	private long date_actuelle = 0;
	
	
	
	public GUI(Carte carte, GUISimulator gui) {
		super();
		this.gui = gui;
		this.carte = carte;
	    gui.setSimulable(this);
	    afficher(carte, gui);
	}
//	public GUI(Carte carte, GUISimulator gui) {
//        this.gui = gui;
//        this.carte = carte;
//        gui.setSimulable(this);
//        afficher(carte, gui);
//	}
	
	
	
	
	public void afficher(Carte carte, GUISimulator gui) {
		gui.reset();
	    for (int i=0; i<carte.getNbLignes(); i++) {
	    	for (int j=0; j<carte.getNbColonnes(); j++) {
	    		System.out.println(i+","+ j+"\n");
	    		switch (carte.getCase(i, j).getNature()) {
	    		case EAU:
	    			gui.addGraphicalElement(new Rectangle(carte.getTailleCases()/2+j*carte.getTailleCases(), carte.getTailleCases()/2+i*carte.getTailleCases(), Color.black, Color.blue, carte.getTailleCases()));
	    			break;
	    		case FORET:
	    			gui.addGraphicalElement(new Rectangle(carte.getTailleCases()/2+j*carte.getTailleCases(), carte.getTailleCases()/2+i*carte.getTailleCases(), Color.black, Color.green, carte.getTailleCases()));
	    			break;
	    		case ROCHE:
	    			gui.addGraphicalElement(new Rectangle(carte.getTailleCases()/2+j*carte.getTailleCases(), carte.getTailleCases()/2+i*carte.getTailleCases(), Color.black, Color.gray, carte.getTailleCases()));
	    			break;
	    		case TERRAIN_LIBRE:
	    			gui.addGraphicalElement(new Rectangle(carte.getTailleCases()/2+j*carte.getTailleCases(), carte.getTailleCases()/2+i*carte.getTailleCases(), Color.black, new Color(91, 60, 17), carte.getTailleCases()));
	    			break;
	    		default:
	    			gui.addGraphicalElement(new Rectangle(carte.getTailleCases()/2+j*carte.getTailleCases(), carte.getTailleCases()/2+i*carte.getTailleCases(), Color.black, new Color(200, 173, 127), carte.getTailleCases()));
	    		}
	    		if (carte.getCase(i, j).getIncendie()>0) {
	    			gui.addGraphicalElement(new Rectangle(carte.getTailleCases()/4+j*carte.getTailleCases(), carte.getTailleCases()/4+i*carte.getTailleCases(), Color.black, new Color(237, 127, 16), carte.getTailleCases()/2));
	    			gui.addGraphicalElement(new Text(carte.getTailleCases()/2+j*carte.getTailleCases(),carte.getTailleCases()/4+i*carte.getTailleCases(),Color.blue, Integer.toString(carte.getCase(i, j).getIncendie())));
	    		}
	    	}
	    }
	    for (Robot robot : carte.getRobots()) {
	    	int i = robot.getPosition().getLigne();
	    	int j = robot.getPosition().getColonne();
	    	gui.addGraphicalElement(new Rectangle(3*carte.getTailleCases()/4+j*carte.getTailleCases(), 3*carte.getTailleCases()/4+i*carte.getTailleCases(), Color.black, Color.red, carte.getTailleCases()/2));
//			gui.addGraphicalElement(new Text(carte.getTailleCases()/2+j*carte.getTailleCases(),carte.getTailleCases()/2+j*carte.getTailleCases(),Color.black, ));
	    }

	    
	}


	public Carte getCarteInitiale() {
		return carteInitiale;
	}




	public void setCarteInitiale(Carte carteInitiale) {
		this.carteInitiale = carteInitiale;
	}




	@Override
    public void next() {
		System.out.println(this.simulateur.getEvents().toString());
        this.date_actuelle += intervalle;
        Deque <Evenement> iterateurEvenement = simulateur.getEvents();
        Evenement event = iterateurEvenement.peekFirst();
        simulateur.execEvenement();
        afficher(carte, gui);
//        while (!simulateur.simulationTerminee() && (event.getDate()<date_actuelle)) {
//        	simulateur.execEvenement();
//        } 
    }

    @Override
    public void restart() {
    	gui.reset();
    	afficher(carteInitiale, gui);
//       
    }



	public Simulateur getSimulateur() {
		return simulateur;
	}



	public void setSimulateur(Simulateur simulateur) {
		this.simulateur = simulateur;
	
	}
	
	
    
    
}