package graphic_interface;
import java.awt.Color;

import carte.Carte;
import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;
import gui.Text;
import robot.Robot;




abstract class GUI implements Simulable {
	static public void afficher(Carte carte, GUISimulator gui) {
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
