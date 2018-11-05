package graphic_interface;
import java.awt.Color;

import carte.Carte;
import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;



abstract class GUI implements Simulable {
	static public void afficher(Carte carte, GUISimulator gui) {
		gui.reset();
	    for (int i=0; i<carte.getNbLignes(); i++) {
	    	for (int j=0; j<carte.getNbColonnes(); j++) {
	    		System.out.println(i+","+ j+"\n");
	    		switch (carte.getCase(i, j).getNature()) {
	    		case EAU:
	    			gui.addGraphicalElement(new Rectangle(carte.getTailleCases()/2+i*carte.getTailleCases(), carte.getTailleCases()/2+j*carte.getTailleCases(), Color.black, Color.blue, carte.getTailleCases()));
	    			break;
	    		case FORET:
	    			gui.addGraphicalElement(new Rectangle(carte.getTailleCases()/2+i*carte.getTailleCases(), carte.getTailleCases()/2+j*carte.getTailleCases(), Color.black, Color.green, carte.getTailleCases()));
	    			break;
	    		case ROCHE:
	    			gui.addGraphicalElement(new Rectangle(carte.getTailleCases()/2+i*carte.getTailleCases(), carte.getTailleCases()/2+j*carte.getTailleCases(), Color.black, Color.gray, carte.getTailleCases()));
	    			break;
	    		case TERRAIN_LIBRE:
	    			gui.addGraphicalElement(new Rectangle(carte.getTailleCases()/2+i*carte.getTailleCases(), carte.getTailleCases()/2+j*carte.getTailleCases(), Color.black, new Color(91, 60, 17), carte.getTailleCases()));
	    			break;
	    		default:
	    			gui.addGraphicalElement(new Rectangle(carte.getTailleCases()/2+i*carte.getTailleCases(), carte.getTailleCases()/2+j*carte.getTailleCases(), Color.black, new Color(200, 173, 127), carte.getTailleCases()));
	    		}
	    	}
	    }
	    
	}
//    @Override
//    public void next() {
//        if (this.xIterator.hasNext())
//            this.x = this.xIterator.next();		
//        if (this.yIterator.hasNext())
//            this.y = this.yIterator.next();		
//        draw();
//    }
//
//    @Override
//    public void restart() {
//        planCoordinates();
//        draw();
//    }
}
