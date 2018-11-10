package simulateur;

import carte.Direction;
import robot.Robot;

public class Deplacement extends Evenement {

	Direction dir;

	public Deplacement(Direction dir, Robot robot) {
		super(robot);
		this.dir = dir;
	}
	
	

	@Override
	public long getDuree() {
//		System.out.println("Taille case =" + robot.getCarte().getTailleReelleCases());
//		System.out.println("vitesse = " +robot.getVitesse(robot.getPosition().getNature()));
		return (long)(robot.getCarte().getTailleReelleCases()*3600/robot.getVitesse(robot.getPosition().getNature()));
	}



	@Override
	public void execute() {
		robot.move(dir);
	}



	@Override
	public String toString() {
		return "Deplacement [dir=" + dir + "]\n";
	}
	
	
	
}
