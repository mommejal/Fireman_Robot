package simulateur;

import robot.Robot;

public class Remplissage extends Evenement {

	public Remplissage(long date, Robot robot) {
		super(date, robot);
	}

	@Override
	public void execute() {
		robot.remplirReservoir();
	}
	
	
	
}
