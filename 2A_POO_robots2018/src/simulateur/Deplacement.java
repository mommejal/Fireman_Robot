package simulateur;

import carte.Direction;
import robot.Robot;

public class Deplacement extends Evenement {

	Direction dir;

	public Deplacement(long date, Direction dir, Robot robot) {
		super(date,robot);
		this.dir = dir;
	}
	
	

	@Override
	public long getDuree() {
		return 1+(long)(robot.getPosition().getTailleCases()/robot.getVitesse()); //
	}



	@Override
	public void execute() {
		robot.move(dir);
	}

}
