package simulateur;

import robot.Robot;

public class Intervention extends Evenement{
	private Robot robot;

	public Intervention(long date, Robot robot) {
		super(date, robot);
	}

	@Override
	public void execute() {
		robot.deverserEau(robot.getPosition().getIncendie());
	}
	
	

}
