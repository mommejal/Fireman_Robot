package simulateur;

import robot.Robot;

public class Remplissage extends Evenement {

	public Remplissage(long date, Robot robot) {
		super(date, robot);
	}
	
	@Override
	public long getDuree() {
		return robot.dureeRemplirReservoir();
	}



	@Override
	public void execute() {
		robot.remplirReservoir();
	}
	
	
	
}
